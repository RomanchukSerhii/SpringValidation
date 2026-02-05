package com.example.springvalidation.presentation.interaction

sealed interface FirstMomentUiEvent {
    data object OpenCamera : FirstMomentUiEvent
    data object RequestCameraPermission : FirstMomentUiEvent
    data object OpenAppSettings : FirstMomentUiEvent
}