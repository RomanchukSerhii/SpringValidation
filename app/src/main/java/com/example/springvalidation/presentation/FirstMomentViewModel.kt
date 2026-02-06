package com.example.springvalidation.presentation

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.springvalidation.data.datastore.CapturedPhotoStorage
import com.example.springvalidation.presentation.interaction.FirstMomentUiAction
import com.example.springvalidation.presentation.interaction.FirstMomentUiEvent
import com.example.springvalidation.presentation.interaction.FirstMomentUiState
import com.example.springvalidation.presentation.permission.CameraPermissionState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Manages UI state and business logic for the First Spring Moment feature.
 *
 * Responsibilities:
 * - Orchestrates camera permission flow (request → grant → open camera)
 * - Persists and restores captured photo URI across app restarts
 * - Emits one-time side effects (camera, permissions, settings) via events
 * - Maintains current permission state and screen state (Initial/Captured)
 */
class FirstMomentViewModel(
    private val capturedPhotoStorage: CapturedPhotoStorage
) : ViewModel() {

    private val _uiState = MutableStateFlow(FirstMomentUiState())
    
    // State restoration: loads saved photo URI when first collected
    val uiState = _uiState
        .onStart { restoreCapturedPhotoIfExists() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L), // Keeps flow active 5s after last subscriber
            FirstMomentUiState()
        )

    // One-time events: camera launch, permission requests, navigation to settings
    private val _events = Channel<FirstMomentUiEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(action: FirstMomentUiAction) {
        when(action) {
            is FirstMomentUiAction.OnPermissionResult -> handlePermissionResult(action.permissionState)
            is FirstMomentUiAction.OnPermissionSynced -> updatePermissionState(action.permissionState)
            is FirstMomentUiAction.OnPhotoCaptured -> onPhotoCaptured(action.uri)
            FirstMomentUiAction.OnCancelDialog -> hidePermissionExplanationDialog()
            FirstMomentUiAction.OnOpenSettings -> handleOpenSettings()
            FirstMomentUiAction.OnPrimaryButtonClicked -> handlePrimaryButtonClick()
        }
    }

    private fun restoreCapturedPhotoIfExists() {
        viewModelScope.launch {
            val savedUri = capturedPhotoStorage.getSavedPhotoUri()

            if (savedUri != null) {
                showCapturedPhoto(savedUri)
            } else {
                showInitialState()
            }
        }
    }

    /**
     * Main button logic: adapts behavior based on permission state.
     * - Granted: opens camera
     * - Unknown/Denied: requests permission
     * - PermanentlyDenied: shows explanation dialog with "Open Settings" option
     */
    private fun handlePrimaryButtonClick() {
        when (uiState.value.permission) {
            CameraPermissionState.Granted -> openCamera()
            CameraPermissionState.Unknown,
            CameraPermissionState.Denied -> requestCameraPermission()
            CameraPermissionState.PermanentlyDenied -> showPermissionExplanationDialog()
        }
    }

    /**
     * Handles permission result from system dialog.
     * On grant, immediately opens camera for smooth UX flow.
     * On permanent denial, shows explanation with settings navigation.
     */
    private fun handlePermissionResult(
        permissionState: CameraPermissionState
    ) {
        updatePermissionState(permissionState)

        when (permissionState) {
            CameraPermissionState.Granted -> openCamera()
            CameraPermissionState.PermanentlyDenied -> showPermissionExplanationDialog()
            CameraPermissionState.Denied,
            CameraPermissionState.Unknown -> Unit
        }
    }

    /**
     * Persists photo URI to survive process death and updates screen state.
     * Storage is async but state updates immediately for responsive UI.
     */
    private fun onPhotoCaptured(uri: Uri) {
        viewModelScope.launch {
            capturedPhotoStorage.savePhotoUri(uri)
        }

        showCapturedPhoto(uri)
    }

    private fun showCapturedPhoto(uri: Uri) {
        _uiState.update {
            it.copy(
                screenState = FirstMomentUiState.ScreenState.Captured(photo = uri)
            )
        }
    }

    private fun openCamera() {
        emitUiEvent(FirstMomentUiEvent.OpenCamera)
    }

    private fun requestCameraPermission() {
        emitUiEvent(FirstMomentUiEvent.RequestCameraPermission)
    }

    private fun handleOpenSettings() {
        hidePermissionExplanationDialog()
        emitUiEvent(FirstMomentUiEvent.OpenAppSettings)
    }

    private fun updatePermissionState(permissionState: CameraPermissionState) {
        _uiState.update {
            it.copy(permission = permissionState)
        }
    }

    private fun showInitialState() {
        _uiState.update {
            it.copy(screenState = FirstMomentUiState.ScreenState.Initial)
        }
    }

    private fun showPermissionExplanationDialog() {
        _uiState.update {
            it.copy(showPermissionExplanationDialog = true)
        }
    }

    private fun hidePermissionExplanationDialog() {
        _uiState.update {
            it.copy(showPermissionExplanationDialog = false)
        }
    }

    private fun emitUiEvent(uiEvent: FirstMomentUiEvent) {
        viewModelScope.launch {
            _events.send(uiEvent)
        }
    }
}