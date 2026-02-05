package com.example.springvalidation.presentation.interaction

import com.example.springvalidation.presentation.permission.CameraPermissionState

/**
 * UI Actions for First Moment screen.
 */
sealed interface FirstMomentUiAction {
    data class OnPermissionResult(val permissionState: CameraPermissionState) : FirstMomentUiAction
    data object OnCancelDialog : FirstMomentUiAction
    data object OnOpenSettings : FirstMomentUiAction
    data object OnPrimaryButtonClicked : FirstMomentUiAction
}