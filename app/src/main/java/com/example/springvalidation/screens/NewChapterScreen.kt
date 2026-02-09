package com.example.springvalidation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.springvalidation.ui.common_components.PrimaryButton
import com.example.springvalidation.ui.common_components.SpringValidationScaffold
import com.example.springvalidation.screens.components.ChapterInputCard
import com.example.springvalidation.screens.components.ReadinessCheckbox
import com.example.springvalidation.ui.theme.SpringValidationTheme

/**
 * New Chapter Check-in Screen.
 * Allows users to set chapter title and confidence level before beginning.
 */
@Composable
fun NewChapterScreen(modifier: Modifier = Modifier) {
    var chapterTitle by remember { mutableStateOf("") }
    var confidenceLevel by remember { mutableFloatStateOf(3f) }
    var isReady by remember { mutableStateOf(false) }
    val isBeginButtonEnabled = chapterTitle.isNotEmpty() && 
                                confidenceLevel > 4f && 
                                isReady

    SpringValidationScaffold(
        title = stringResource(R.string.new_chapter),
        modifier = modifier
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Header text
                Text(
                    text = stringResource(R.string.check_in_before_begin),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )

                // Chapter input card with title and confidence level
                ChapterInputCard(
                    chapterTitle = chapterTitle,
                    onChapterTitleChange = { chapterTitle = it },
                    confidenceLevel = confidenceLevel,
                    onConfidenceLevelChange = { confidenceLevel = it }
                )

                // Readiness confirmation checkbox
                ReadinessCheckbox(
                    checked = isReady,
                    onCheckedChange = { isReady = it }
                )
            }

            // Begin Chapter button at the bottom
            PrimaryButton(
                text = stringResource(R.string.begin_chapter),
                onClick = { /* TODO: Handle chapter creation */ },
                enabled = isBeginButtonEnabled,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
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
