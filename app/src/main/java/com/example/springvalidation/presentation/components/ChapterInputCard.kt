package com.example.springvalidation.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.springvalidation.R
import com.example.springvalidation.ui.common_components.ContentCard
import com.example.springvalidation.ui.common_components.LabeledTextField
import com.example.springvalidation.ui.theme.SpringValidationTheme

/**
 * A card component for chapter input fields.
 * Contains title input and confidence level slider wrapped in ContentCard.
 */
@Composable
fun ChapterInputCard(
    chapterTitle: String,
    onChapterTitleChange: (String) -> Unit,
    confidenceLevel: Float,
    onConfidenceLevelChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    ContentCard(modifier = modifier) {
        Column {
            // Chapter title field
            LabeledTextField(
                label = stringResource(R.string.chapter_title),
                value = chapterTitle,
                onValueChange = onChapterTitleChange,
                placeholder = stringResource(R.string.chapter_name_placeholder)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Confidence level slider
            ConfidenceSlider(
                value = confidenceLevel,
                onValueChange = onConfidenceLevelChange
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ChapterInputCardPreview() {
    SpringValidationTheme {
        ChapterInputCard(
            chapterTitle = "Preview Chapter",
            onChapterTitleChange = {},
            confidenceLevel = 3f,
            onConfidenceLevelChange = {}
        )
    }
}