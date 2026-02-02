package com.example.springvalidation.ui.screens

/**
 * UI Actions for New Note screen.
 */
sealed interface NewNoteUiAction {
    data class TitleChanged(val title: String) : NewNoteUiAction

    data class DescriptionChanged(val description: String) : NewNoteUiAction

    data class KeepDraftChanged(val keepDraft: Boolean) : NewNoteUiAction

    data object SaveNoteClicked : NewNoteUiAction
}
