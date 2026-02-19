package com.example.springvalidation.presentation

import androidx.lifecycle.ViewModel
import com.example.springvalidation.presentation.interaction.NewChapterUiAction
import com.example.springvalidation.presentation.interaction.NewChapterUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for the New Chapter screen.
 */
class NewChapterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(NewChapterUiState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: NewChapterUiAction) {
        when (action) {
            is NewChapterUiAction.OnChapterTitleChange -> updateTitle(action.title)
            is NewChapterUiAction.OnConfidenceLevelChange -> updateConfidence(action.confidence)
            is NewChapterUiAction.OnReadinessCheckboxChange -> updateReadiness(action.checked)
            NewChapterUiAction.OnBeginChapterClicked -> handleBeginChapter()
        }
    }

    private fun updateTitle(title: String) {
        _uiState.update { it.copy(chapterTitle = title) }
    }

    private fun updateConfidence(confidence: Float) {
        _uiState.update { it.copy(confidenceLevel = confidence) }
    }

    private fun updateReadiness(ready: Boolean) {
        _uiState.update { it.copy(isReady = ready) }
    }

    private fun handleBeginChapter() {
        // ... action logic ...
    }
}
