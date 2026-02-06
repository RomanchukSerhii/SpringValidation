package com.example.springvalidation.presentation.permission

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

/**
 * Resolves camera permission state from Android system APIs.
 */
object CameraPermissionResolver {

    /**
     * Determines permission state from permission request result.
     * 
     * State logic:
     * - Granted: User approved
     * - PermanentlyDenied: User denied twice or checked "Don't ask again"
     *   (shouldShowRequestPermissionRationale returns false after denial)
     * - Denied: User denied once, can be requested again
     */
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

    /**
     * Reads current permission state without requesting.
     * 
     * Used on screen resume to detect if user granted permission in Settings.
     * Returns Unknown (not Denied) since we can't distinguish denial without Activity context.
     */
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