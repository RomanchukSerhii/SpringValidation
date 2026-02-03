package com.example.springvalidation.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.springvalidation.ui.common_components.SpringValidationScaffold
import com.example.springvalidation.ui.theme.SpringValidationTheme

@Composable
fun NewChapterScreen() {

    SpringValidationScaffold(
        title = "First Spring Moment",
    ) { paddingValues ->

    }
}

@Preview(
    showBackground = true
)
@Composable
fun NewChapterScreenPreview() {
    SpringValidationTheme {
        NewChapterScreen()
    }
}