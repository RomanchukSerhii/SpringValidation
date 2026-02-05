package com.example.springvalidation.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.springvalidation.R
import com.example.springvalidation.ui.common_components.OutlineButton
import com.example.springvalidation.ui.common_components.PrimaryButton
import com.example.springvalidation.ui.theme.SpringValidationTheme

@Composable
fun CameraPermissionPrompt(
    onCancel: () -> Unit,
    onOpenSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
            .background(
                MaterialTheme.colorScheme.surface,
                RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp)
            )
            .padding(horizontal = 16.dp)
            .padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        CameraPermissionRationale()

        CameraPermissionActions(
            onCancel = onCancel,
            onOpenSettings = onOpenSettings,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
fun CameraPermissionRationale(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.camera_permission_title),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium
            ),
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = stringResource(R.string.camera_permission_description),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CameraPermissionActions(
    onCancel: () -> Unit,
    onOpenSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        OutlineButton(
            text = stringResource(R.string.cancel),
            onClick = onCancel,
            modifier = Modifier.weight(1f)
        )

        PrimaryButton(
            text = stringResource(R.string.open_settings),
            onClick = onOpenSettings,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun CameraPermissionPromptPreview() {
    SpringValidationTheme {
        CameraPermissionPrompt(
            onCancel = {},
            onOpenSettings = {}
        )
    }
}