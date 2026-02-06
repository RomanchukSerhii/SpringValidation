package com.example.springvalidation.data.datastore

import android.net.Uri
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first

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

    suspend fun getSavedPhotoUri(): Uri? {
        val prefs = dataStore.data.first()
        return prefs[KEY_PHOTO_URI]?.let(Uri::parse)
    }

    suspend fun clear() {
        dataStore.edit { prefs ->
            prefs.remove(KEY_PHOTO_URI)
        }
    }
}