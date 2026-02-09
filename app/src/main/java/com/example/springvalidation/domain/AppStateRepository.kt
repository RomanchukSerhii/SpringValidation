package com.example.springvalidation.domain

import kotlinx.coroutines.flow.Flow

/**
 * Domain interface for app state repository.
 * Abstracts state persistence implementation.
 */
interface AppStateRepository {
    /**
     * Flow that emits whether the user has started a new morning.
     */
    val hasStartedNewMorning: Flow<Boolean>
    
    /**
     * Marks that the user has started a new morning.
     */
    suspend fun setNewMorningStarted()
    
    /**
     * Resets the morning state.
     */
    suspend fun resetMorningState()
}
