package com.example.springvalidation.presentation.interaction

import android.net.Uri
import com.example.springvalidation.presentation.permission.CameraPermissionState

/**
 * UI state for the First Spring Moment screen.
 *
 * @property screenState Current screen content (loading, prompt, or captured photo)
 * @property permission Current camera permission status; drives button behavior
 * @property showPermissionExplanationDialog Shows when permission is permanently denied
 */
data class FirstMomentUiState(
    val screenState: ScreenState = ScreenState.Loading,
    val permission: CameraPermissionState = CameraPermissionState.Unknown,
    val showPermissionExplanationDialog: Boolean = false
) {
    sealed class ScreenState {
        /** Shown while loading saved photo from storage */
        data object Loading : ScreenState()
        
        /** No photo captured yet; shows prompt to take first photo */
        data object Initial : ScreenState()
        
        /** Photo captured; displays image with option to update */
        data class Captured(val photo: Uri) : ScreenState()
    }
}