package com.example.springvalidation.presentation.interaction

import android.net.Uri
import com.example.springvalidation.presentation.permission.CameraPermissionState

/**
 * User actions from the First Moment screen.
 * 
 * ViewModel processes these to update state or emit side effects.
 */
sealed interface FirstMomentUiAction {
    /** Permission dialog result from system */
    data class OnPermissionResult(val permissionState: CameraPermissionState) : FirstMomentUiAction
    
    /** Permission synced after returning from Settings */
    data class OnPermissionSynced(val permissionState: CameraPermissionState) : FirstMomentUiAction
    
    /** Photo successfully captured from camera */
    data class OnPhotoCaptured(val uri: Uri) : FirstMomentUiAction
    
    data object OnCancelDialog : FirstMomentUiAction
    data object OnOpenSettings : FirstMomentUiAction
    
    /** Primary button: behavior adapts to permission state */
    data object OnPrimaryButtonClicked : FirstMomentUiAction
}