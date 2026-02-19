package com.example.springvalidation.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.springvalidation.R
import com.example.springvalidation.presentation.components.ChapterInputCard
import com.example.springvalidation.presentation.components.ReadinessCheckbox
import com.example.springvalidation.presentation.interaction.NewChapterUiAction
import com.example.springvalidation.presentation.interaction.NewChapterUiState
import com.example.springvalidation.ui.common_components.PrimaryButton
import com.example.springvalidation.ui.common_components.SpringValidationScaffold
import com.example.springvalidation.ui.theme.SpringValidationTheme

/**
 * New Chapter Check-in Screen.
 * Allows users to set chapter title and confidence level before beginning.
 */
@Composable
fun NewChapterScreen(
    modifier: Modifier = Modifier,
    viewModel: NewChapterViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    NewChapterContent(
        uiState = uiState,
        onAction = viewModel::onAction,
        modifier = modifier
    )
}

@Composable
fun NewChapterContent(
    uiState: NewChapterUiState,
    onAction: (NewChapterUiAction) -> Unit,
    modifier: Modifier = Modifier
) {
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
                    chapterTitle = uiState.chapterTitle,
                    onChapterTitleChange = { onAction(NewChapterUiAction.OnChapterTitleChange(it)) },
                    confidenceLevel = uiState.confidenceLevel,
                    onConfidenceLevelChange = {
                        onAction(
                            NewChapterUiAction.OnConfidenceLevelChange(
                                it
                            )
                        )
                    }
                )

                // Readiness confirmation checkbox
                ReadinessCheckbox(
                    checked = uiState.isReady,
                    onCheckedChange = { onAction(NewChapterUiAction.OnReadinessCheckboxChange(it)) }
                )
            }

            // Begin Chapter button at the bottom
            PrimaryButton(
                text = stringResource(R.string.begin_chapter),
                onClick = { onAction(NewChapterUiAction.OnBeginChapterClicked) },
                enabled = uiState.isBeginButtonEnabled,
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
        NewChapterContent(
            uiState = NewChapterUiState(),
            onAction = {}
        )
    }
}
