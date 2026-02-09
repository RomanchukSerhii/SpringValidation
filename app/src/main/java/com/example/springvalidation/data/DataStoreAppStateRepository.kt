package com.example.springvalidation.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.springvalidation.domain.AppStateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Extension property to create DataStore instance.
 */
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_state")

/**
 * DataStore implementation of AppStateRepository.
 * Handles persistence of user progress through DataStore.
 */
class DataStoreAppStateRepository(private val context: Context) : AppStateRepository {
    
    private object PreferencesKeys {
        val HAS_STARTED_NEW_MORNING = booleanPreferencesKey("has_started_new_morning")
    }
    
    /**
     * Flow that emits whether the user has started a new morning.
     */
    override val hasStartedNewMorning: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.HAS_STARTED_NEW_MORNING] ?: false
        }
    
    /**
     * Marks that the user has started a new morning.
     * This state persists across app launches.
     */
    override suspend fun setNewMorningStarted() {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.HAS_STARTED_NEW_MORNING] = true
        }
    }
    
    /**
     * Resets the morning state.
     * Useful for testing purposes.
     */
    override suspend fun resetMorningState() {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.HAS_STARTED_NEW_MORNING] = false
        }
    }
}
