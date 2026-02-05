package com.example.springvalidation.presentation.permission

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

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

    fun readCurrentState(
        context: Context
    ): CameraPermissionState {
        val isGranted = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        return if (isGranted) {
            CameraPermissionState.Granted
        } else {
            CameraPermissionState.Unknown
        }
    }
}