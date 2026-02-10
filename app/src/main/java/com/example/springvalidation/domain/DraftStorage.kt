package com.example.springvalidation.domain

import kotlinx.coroutines.flow.Flow

/**
 * Domain interface for draft storage operations.
 * Abstracts away the implementation details of how drafts are persisted.
 */
interface DraftStorage {
    val draftFlow: Flow<DraftState>

    suspend fun saveDraft(title: String, description: String)

    suspend fun clearDraft()
}

/**
 * Data class representing draft state.
 */
data class DraftState(
    val title: String = "",
    val description: String = ""
)
