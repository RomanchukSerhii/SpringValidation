package com.example.springvalidation.domain

import android.net.Uri

interface CapturedPhotoStorage {
    suspend fun savePhotoUri(uri: Uri)
    suspend fun getSavedPhotoUri(): Uri?
}