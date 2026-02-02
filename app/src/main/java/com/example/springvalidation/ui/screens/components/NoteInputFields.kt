package com.example.springvalidation.ui.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.springvalidation.ui.common_components.ContentCard
import com.example.springvalidation.ui.common_components.LabeledTextField
import com.example.springvalidation.ui.theme.SpringValidationTheme

/**
 * Note input fields component.
 * Contains title and description input fields for creating a new note.
 *
 * @param title Current title value
 * @param onTitleChange Callback when title changes
 * @param description Current description value
 * @param onDescriptionChange Callback when description changes
 * @param modifier Optional modifier
 */
@Composable
fun NoteInputFields(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    ContentCard(
        modifier = modifier.fillMaxWidth()
    ) {
        Column() {
            // Title input field
            LabeledTextField(
                label = "Title",
                value = title,
                onValueChange = onTitleChange,
                placeholder = "Enter a title",
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Description input field (multi-line)
            LabeledTextField(
                label = "Description",
                value = description,
                onValueChange = onDescriptionChange,
                placeholder = "Write your thoughts...",
                singleLine = false,
                maxLines = 5,
                minLines = 3
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteInputFieldsPreview() {
    SpringValidationTheme {
        var title by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }

        NoteInputFields(
            title = title,
            onTitleChange = { title = it },
            description = description,
            onDescriptionChange = { description = it },
            modifier = Modifier.padding(16.dp)
        )
    }
}
