package com.example.springvalidation.presentation.interaction

/**
 * User actions for the New Chapter screen.
 */
sealed interface NewChapterUiAction {
    data class OnChapterTitleChange(val title: String) : NewChapterUiAction
    data class OnConfidenceLevelChange(val confidence: Float) : NewChapterUiAction
    data class OnReadinessCheckboxChange(val checked: Boolean) : NewChapterUiAction
    data object OnBeginChapterClicked : NewChapterUiAction
}
