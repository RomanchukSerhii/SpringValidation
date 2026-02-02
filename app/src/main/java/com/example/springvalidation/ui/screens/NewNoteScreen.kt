package com.example.springvalidation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.springvalidation.R
import com.example.springvalidation.data.DraftRepository
import com.example.springvalidation.ui.common_components.PrimaryButton
import com.example.springvalidation.ui.common_components.SpringValidationScaffold
import com.example.springvalidation.ui.screens.components.NoteInputFields
import com.example.springvalidation.ui.theme.SpringValidationTheme

/**
 * Root composable for New Note screen.
 * Creates ViewModel with DraftRepository and observes state.
 */
@Composable
fun NewNoteRoute(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val draftRepository = remember { DraftRepository(context) }
    val viewModel: NewNoteViewModel = viewModel(
        factory = NewNoteViewModelFactory(draftRepository)
    )
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NewNoteScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
        modifier = modifier
    )
}

/**
 * Stateless New Note Screen.
 * Displays note input fields with keep draft toggle and save button.
 *
 * @param uiState Current UI state
 * @param onAction Callback for UI actions
 * @param modifier Optional modifier
 */
@Composable
fun NewNoteScreen(
    uiState: NewNoteUiState,
    onAction: (NewNoteUiAction) -> Unit,
    modifier: Modifier = Modifier
) {
    SpringValidationScaffold(
        title = stringResource(R.string.new_note),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Note input fields with keep draft toggle
            NoteInputFields(
                title = uiState.title,
                onTitleChange = { onAction(NewNoteUiAction.TitleChanged(it)) },
                description = uiState.description,
                onDescriptionChange = { onAction(NewNoteUiAction.DescriptionChanged(it)) },
                keepDraft = uiState.keepDraft,
                onKeepDraftChange = { onAction(NewNoteUiAction.KeepDraftChanged(it)) }
            )

            Spacer(modifier = Modifier.weight(1f))

            // Save Note button
            PrimaryButton(
                text = stringResource(R.string.save_note),
                onClick = { onAction(NewNoteUiAction.SaveNoteClicked) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewNoteScreenPreview() {
    SpringValidationTheme {
        NewNoteScreen(
            uiState = NewNoteUiState(
                title = "",
                description = "",
                keepDraft = false
            ),
            onAction = {}
        )
    }
}

@Preview(showBackground = true, name = "With Content")
@Composable
fun NewNoteScreenWithContentPreview() {
    SpringValidationTheme {
        NewNoteScreen(
            uiState = NewNoteUiState(
                title = "My Note Title",
                description = "Some description text here...",
                keepDraft = true
            ),
            onAction = {}
        )
    }
}
