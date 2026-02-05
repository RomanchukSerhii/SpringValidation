package com.example.springvalidation.presentation.interaction

import android.net.Uri
import com.example.springvalidation.presentation.permission.CameraPermissionState

data class FirstMomentUiState(
    val screenState: ScreenState = ScreenState.Initial,
    val permission: CameraPermissionState = CameraPermissionState.Unknown,
    val showPermissionExplanationDialog: Boolean = false
) {
    sealed interface ScreenState {
        data object Initial : ScreenState
        data class Captured(val photo: Uri) : ScreenState
    }
}