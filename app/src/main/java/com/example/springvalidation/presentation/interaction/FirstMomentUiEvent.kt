package com.example.springvalidation.presentation.interaction

/**
 * One-time side effects emitted by ViewModel to trigger system interactions.
 * 
 * Consumed by Root composable via ObserveAsEvents.
 * Using events (not state) ensures these are not replayed on recomposition/config change.
 */
sealed interface FirstMomentUiEvent {
    data object OpenCamera : FirstMomentUiEvent
    data object RequestCameraPermission : FirstMomentUiEvent
    data object OpenAppSettings : FirstMomentUiEvent
}