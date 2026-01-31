package com.example.springvalidation.ui.common_components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.springvalidation.ui.theme.SpringValidationTheme

/**
 * Primary filled button with primary color background.
 *
 * @param text Button text
 * @param onClick Click callback
 * @param modifier Optional modifier
 * @param enabled Whether the button is enabled (default: true)
 */
@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
            disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
        ),
        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium
            )
        )
    }
}

/**
 * Outlined button with border and transparent background.
 *
 * @param text Button text
 * @param onClick Click callback
 * @param modifier Optional modifier
 * @param enabled Whether the button is enabled (default: true)
 */
@Composable
fun OutlineButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            disabledContentColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonsPreview() {
    SpringValidationTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Full width buttons
            PrimaryButton(
                text = "Open settings",
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            )

            OutlineButton(
                text = "Cancel",
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )

            // Side by side buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            ) {
                OutlineButton(
                    text = "Cancel",
                    onClick = { },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )

                PrimaryButton(
                    text = "Open settings",
                    onClick = { },
                    modifier = Modifier.weight(1f)
                )
            }

            // Disabled state
            PrimaryButton(
                text = "Disabled",
                onClick = { },
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            )
        }
    }
}
