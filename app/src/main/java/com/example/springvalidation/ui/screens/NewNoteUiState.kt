package com.example.springvalidation.ui.screens

/**
 * UI State for New Note screen.
 *
 * @param title Current note title
 * @param description Current note description
 * @param keepDraft Whether to keep draft after process death
 */
data class NewNoteUiState(
    val title: String = "",
    val description: String = "",
    val keepDraft: Boolean = false
)
