package com.example.springvalidation.presentation.interaction

/**
 * UI state for the New Chapter screen.
 * 
 * Includes current input values and calculated button availability.
 */
data class NewChapterUiState(
    val chapterTitle: String = "",
    val confidenceLevel: Float = 3f,
    val isReady: Boolean = false
) {
    val isBeginButtonEnabled: Boolean
        get() = chapterTitle.isNotEmpty() && 
                confidenceLevel > 4f && 
                isReady
}
