package com.example.springvalidation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.springvalidation.ui.common_components.SpringValidationScaffold
import com.example.springvalidation.ui.screens.components.NoteInputFields
import com.example.springvalidation.ui.theme.SpringValidationTheme

/**
 * New Note Screen.
 * Allows users to create a new note with title and description.
 * Includes functionality to keep draft after process death.
 */
@Composable
fun NewNoteScreen(modifier: Modifier = Modifier) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var keepDraft by remember { mutableStateOf(false) }

    SpringValidationScaffold(
        title = "New Note",
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Note input fields
            NoteInputFields(
                title = title,
                onTitleChange = { title = it },
                description = description,
                onDescriptionChange = { description = it }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // TODO: Add Keep draft toggle
            
            Spacer(modifier = Modifier.weight(1f))

            // TODO: Add Save Note button
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewNoteScreenPreview() {
    SpringValidationTheme {
        NewNoteScreen()
    }
}
