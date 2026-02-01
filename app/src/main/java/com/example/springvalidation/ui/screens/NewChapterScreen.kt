package com.example.springvalidation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.springvalidation.R
import com.example.springvalidation.ui.common_components.SpringValidationScaffold
import com.example.springvalidation.ui.screens.components.ChapterInputCard
import com.example.springvalidation.ui.theme.SpringValidationTheme

/**
 * New Chapter Check-in Screen.
 * Allows users to set chapter title and confidence level before beginning.
 */
@Composable
fun NewChapterScreen(modifier: Modifier = Modifier) {
    var chapterTitle by remember { mutableStateOf("") }
    var confidenceLevel by remember { mutableFloatStateOf(3f) }

    SpringValidationScaffold(
        title = stringResource(R.string.new_chapter),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header text
            Text(
                text = stringResource(R.string.check_in_before_begin),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Chapter input card with title and confidence level
            ChapterInputCard(
                chapterTitle = chapterTitle,
                onChapterTitleChange = { chapterTitle = it },
                confidenceLevel = confidenceLevel,
                onConfidenceLevelChange = { confidenceLevel = it }
            )
        }
    }
}

@Preview(
    showBackground = true)
@Composable
fun NewChapterScreenPreview() {
    SpringValidationTheme {
        NewChapterScreen()
    }
}
