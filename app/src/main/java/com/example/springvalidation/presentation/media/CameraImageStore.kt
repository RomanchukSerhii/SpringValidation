package com.example.springvalidation.presentation.media

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import java.io.IOException

/**
 * Manages MediaStore URIs for camera capture.
 * 
 * Uses scoped storage (Android 10+) approach: pre-creates MediaStore entry,
 * camera app writes to it, then we either keep or delete based on capture result.
 */
object CameraImageStore {
    /**
     * Creates a MediaStore URI for camera to write to.
     * Must be created before launching TakePicture contract.
     * 
     * @throws IOException if MediaStore entry creation fails
     */
    fun createImageUri(context: Context): Uri {
        val contentResolver = context.contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "IMG_${System.currentTimeMillis()}.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }

        return contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        ) ?: throw IOException("Failed to create MediaStore entry")
    }

    /**
     * Attempts to delete unused MediaStore entry (e.g., when user cancels camera).
     * 
     * Best-effort: logs warning on failure but doesn't crash.
     * Failed deletion leaves an empty file in MediaStore but is non-critical.
     */
    fun deleteImage(
        context: Context,
        uri: Uri
    ) {
        try {
            context.contentResolver.delete(uri, null, null)
        } catch (e: Exception) {
            Log.w(
                "CameraImageStore",
                "Failed to delete temporary camera image. " +
                        "This may leave an unused file in MediaStore. Uri=$uri",
                e
            )
        }
    }
}