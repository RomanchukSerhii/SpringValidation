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

class FirstMomentViewModel(
    private val capturedPhotoStorage: CapturedPhotoStorage
) : ViewModel() {

    private val _uiState = MutableStateFlow(FirstMomentUiState())
    val uiState = _uiState
        .onStart { restoreCapturedPhotoIfExists() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            FirstMomentUiState()
        )

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

    private fun handlePrimaryButtonClick() {
        when (uiState.value.permission) {
            CameraPermissionState.Granted -> openCamera()
            CameraPermissionState.Unknown,
            CameraPermissionState.Denied -> requestCameraPermission()
            CameraPermissionState.PermanentlyDenied -> showPermissionExplanationDialog()
        }
    }

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