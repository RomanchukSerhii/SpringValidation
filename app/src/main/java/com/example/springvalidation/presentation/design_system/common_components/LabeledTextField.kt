package com.example.springvalidation.presentation.design_system.common_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.springvalidation.presentation.design_system.theme.SpringValidationTheme

/**
 * Universal text field component with a label.
 * Supports both single-line and multi-line input.
 *
 * @param label The label text displayed above the text field
 * @param value Current text value
 * @param onValueChange Callback when text changes
 * @param modifier Optional modifier
 * @param placeholder Optional placeholder text
 * @param singleLine Whether the field is single-line (default: true)
 * @param maxLines Maximum number of lines for multi-line input (default: 1)
 * @param minLines Minimum number of lines for multi-line input (default: 1)
 */
@Composable
fun LabeledTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    singleLine: Boolean = true,
    maxLines: Int = 1,
    minLines: Int = 1
) {
    Column(modifier = modifier.fillMaxWidth()) {
        // Label with Medium weight
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium
            ),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Text field
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LabeledTextFieldPreview() {
    SpringValidationTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            var titleValue by remember { mutableStateOf("") }
            var descriptionValue by remember { mutableStateOf("") }

            // Single-line text field
            LabeledTextField(
                label = "Title",
                value = titleValue,
                onValueChange = { titleValue = it },
                placeholder = "Enter a title",
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Multi-line text field
            LabeledTextField(
                label = "Description",
                value = descriptionValue,
                onValueChange = { descriptionValue = it },
                placeholder = "Write your thoughts...",
                singleLine = false,
                maxLines = 5,
                minLines = 3
            )
        }
    }
}
