package com.example.springvalidation.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.springvalidation.data.DraftRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for New Note screen.
 * Manages note draft state and persistence using DraftRepository.
 *
 * @param draftRepository Repository for draft persistence
 */
class NewNoteViewModel(
    private val draftRepository: DraftRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewNoteUiState())
    val uiState: StateFlow<NewNoteUiState> = _uiState.asStateFlow()

    private var saveDraftJob: Job? = null

    init {
        viewModelScope.launch {
            draftRepository.draftFlow.collect { draftState ->
                _uiState.update { currentState ->
                    currentState.copy(
                        title = draftState.title,
                        description = draftState.description
                    )
                }
            }
        }
    }

    /**
     * Handles UI actions.
     */
    fun onAction(action: NewNoteUiAction) {
        when (action) {
            is NewNoteUiAction.TitleChanged -> updateTitle(action.title)
            is NewNoteUiAction.DescriptionChanged -> updateDescription(action.description)
            is NewNoteUiAction.KeepDraftChanged -> updateKeepDraft(action.keepDraft)
            NewNoteUiAction.SaveNoteClicked -> saveNote()
        }
    }

    private fun updateTitle(newTitle: String) {
        _uiState.update { it.copy(title = newTitle) }
        scheduleDraftSave()
    }

    private fun updateDescription(newDescription: String) {
        _uiState.update { it.copy(description = newDescription) }
        scheduleDraftSave()
    }

    private fun updateKeepDraft(newKeepDraft: Boolean) {
        _uiState.update { it.copy(keepDraft = newKeepDraft) }
        saveDraftJob?.cancel()
        saveDraftIfNeeded()
    }

    private fun scheduleDraftSave() {
        // Cancel previous save job
        saveDraftJob?.cancel()
        
        // Only schedule save if keepDraft is enabled
        if (!_uiState.value.keepDraft) return
        
        // Schedule new save with 700ms debounce
        saveDraftJob = viewModelScope.launch {
            delay(700)
            saveDraftIfNeeded()
        }
    }

    private fun saveDraftIfNeeded() {
        viewModelScope.launch {
            val currentState = _uiState.value
            if (currentState.keepDraft) {
                draftRepository.saveDraft(
                    title = currentState.title,
                    description = currentState.description
                )
            } else {
                draftRepository.clearDraft()
            }
        }
    }

    private fun saveNote() {
        // TODO: Add note saving logic here (out of scope for this challenge)
        
        // Clear draft after saving
        clearDraft()
    }

    private fun clearDraft() {
        viewModelScope.launch {
            draftRepository.clearDraft()
        }
        _uiState.update {
            NewNoteUiState(
                title = "",
                description = "",
                keepDraft = false
            )
        }
    }
}
