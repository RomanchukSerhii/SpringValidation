package com.example.springvalidation.presentation.media

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import java.io.IOException

object CameraImageStore {
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