package com.example.springvalidation.data.datastore

import android.net.Uri
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first

/**
 * Persists captured photo URI to DataStore for state restoration.
 * 
 * Survives process death and app restarts.
 * Stores URI as string; actual image lives in MediaStore.
 */
class CapturedPhotoStorage(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        private val KEY_PHOTO_URI = stringPreferencesKey("captured_photo_uri")
    }

    suspend fun savePhotoUri(uri: Uri) {
        dataStore.edit { prefs ->
            prefs[KEY_PHOTO_URI] = uri.toString()
        }
    }

    /** Returns null if no photo has been captured yet */
    suspend fun getSavedPhotoUri(): Uri? {
        val prefs = dataStore.data.first()
        return prefs[KEY_PHOTO_URI]?.let(Uri::parse)
    }
}