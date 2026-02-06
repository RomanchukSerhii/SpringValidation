package com.example.springvalidation.presentation.interaction

import android.net.Uri
import com.example.springvalidation.presentation.permission.CameraPermissionState

/**
 * UI Actions for First Moment screen.
 */
sealed interface FirstMomentUiAction {
    data class OnPermissionResult(val permissionState: CameraPermissionState) : FirstMomentUiAction
    data class OnPermissionSynced(val permissionState: CameraPermissionState) : FirstMomentUiAction
    data class OnPhotoCaptured(val uri: Uri) : FirstMomentUiAction
    data object OnCancelDialog : FirstMomentUiAction
    data object OnOpenSettings : FirstMomentUiAction
    data object OnPrimaryButtonClicked : FirstMomentUiAction
}