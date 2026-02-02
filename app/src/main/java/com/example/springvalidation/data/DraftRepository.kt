package com.example.springvalidation.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "note_draft")

/**
 * Repository for managing note draft persistence using DataStore.
 */
class DraftRepository(private val context: Context) {

    companion object {
        private val KEY_TITLE = stringPreferencesKey("draft_title")
        private val KEY_DESCRIPTION = stringPreferencesKey("draft_description")
    }

    /**
     * Flow of current draft state.
     */
    val draftFlow: Flow<DraftState> = context.dataStore.data.map { preferences ->
        DraftState(
            title = preferences[KEY_TITLE] ?: "",
            description = preferences[KEY_DESCRIPTION] ?: ""
        )
    }

    suspend fun saveDraft(title: String, description: String) {
        context.dataStore.edit { preferences ->
            preferences[KEY_TITLE] = title
            preferences[KEY_DESCRIPTION] = description
        }
    }

    suspend fun clearDraft() {
        context.dataStore.edit { preferences ->
            preferences.remove(KEY_TITLE)
            preferences.remove(KEY_DESCRIPTION)
        }
    }
}

/**
 * Data class representing draft state.
 */
data class DraftState(
    val title: String = "",
    val description: String = ""
)
