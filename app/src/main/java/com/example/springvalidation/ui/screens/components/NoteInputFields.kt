package com.example.springvalidation.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.springvalidation.ui.common_components.ContentCard
import com.example.springvalidation.ui.common_components.LabeledTextField
import com.example.springvalidation.ui.common_components.Switcher
import com.example.springvalidation.ui.theme.SpringValidationTheme

/**
 * Note input fields component.
 * Contains title, description input fields and keep draft toggle for creating a new note.
 *
 * @param title Current title value
 * @param onTitleChange Callback when title changes
 * @param description Current description value
 * @param onDescriptionChange Callback when description changes
 * @param keepDraft Current keep draft toggle state
 * @param onKeepDraftChange Callback when keep draft toggle changes
 * @param modifier Optional modifier
 */
@Composable
fun NoteInputFields(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    keepDraft: Boolean,
    onKeepDraftChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    ContentCard(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Title input field
            LabeledTextField(
                label = "Title",
                value = title,
                onValueChange = onTitleChange,
                placeholder = "Enter a title",
                singleLine = true
            )

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

            // Keep draft toggle
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Keep draft",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.width(12.dp))

                Switcher(
                    checked = keepDraft,
                    onCheckedChange = onKeepDraftChange
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteInputFieldsPreview() {
    SpringValidationTheme {
        var title by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }
        var keepDraft by remember { mutableStateOf(false) }

        NoteInputFields(
            title = title,
            onTitleChange = { title = it },
            description = description,
            onDescriptionChange = { description = it },
            keepDraft = keepDraft,
            onKeepDraftChange = { keepDraft = it },
            modifier = Modifier.padding(16.dp)
        )
    }
}
