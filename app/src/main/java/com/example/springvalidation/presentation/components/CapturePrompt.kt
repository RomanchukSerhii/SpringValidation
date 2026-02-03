package com.example.springvalidation.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.springvalidation.R
import com.example.springvalidation.ui.theme.SpringValidationTheme

/**
 * A composable component that displays an empty camera state.
 * Shows a camera icon and a prompt text encouraging the user
 * to capture their first spring moment.
 */
@Composable
fun CapturePrompt() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_camera),
            contentDescription = null,
            tint = Color.Unspecified
        )
        Text(
            text = stringResource(R.string.capture_text),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ChapterInputCardPreview() {
    SpringValidationTheme {
        CapturePrompt()
    }
}