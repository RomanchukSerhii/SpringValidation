package com.example.springvalidation.presentation.permission

/**
 * Camera permission lifecycle states.
 * 
 * Flow: Unknown → (request) → Granted/Denied → (request again) → PermanentlyDenied
 * PermanentlyDenied requires manual grant via Settings.
 */
sealed interface CameraPermissionState {
    /** Initial state; permission not yet requested or checked */
    data object Unknown : CameraPermissionState
    
    /** User granted permission */
    data object Granted : CameraPermissionState
    
    /** User denied once; can request again */
    data object Denied : CameraPermissionState
    
    /** User denied multiple times or checked "Don't ask again"; must use Settings */
    data object PermanentlyDenied : CameraPermissionState
}