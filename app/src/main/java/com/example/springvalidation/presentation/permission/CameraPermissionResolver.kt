package com.example.springvalidation.presentation.permission

import android.Manifest
import android.app.Activity

object CameraPermissionResolver {

    fun resolve(
        isGranted: Boolean,
        activity: Activity?
    ): CameraPermissionState {
        return when {
            isGranted -> {
                CameraPermissionState.Granted
            }

            activity?.shouldShowRequestPermissionRationale(
                Manifest.permission.CAMERA
            ) == false -> {
                CameraPermissionState.PermanentlyDenied
            }

            else -> {
                CameraPermissionState.Denied
            }
        }
    }
}