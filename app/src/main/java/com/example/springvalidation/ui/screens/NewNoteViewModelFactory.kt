package com.example.springvalidation.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.springvalidation.data.DraftRepository

/**
 * Factory for creating NewNoteViewModel with DraftRepository dependency.
 */
class NewNoteViewModelFactory(
    private val draftRepository: DraftRepository
) : ViewModelProvider.Factory {
    
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewNoteViewModel::class.java)) {
            return NewNoteViewModel(draftRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
