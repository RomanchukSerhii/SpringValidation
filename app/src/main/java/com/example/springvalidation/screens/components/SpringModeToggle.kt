package com.example.springvalidation.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.springvalidation.R
import com.example.springvalidation.ui.common_components.Switcher
import com.example.springvalidation.ui.theme.SpringValidationTheme

/**
 * A toggle component for switching Spring mode on/off.
 * Displays a label with a switcher control.
 *
 * @param checked Current state of the toggle
 * @param onCheckedChange Callback when toggle state changes
 * @param enabled Whether the toggle is enabled
 * @param modifier Optional modifier for the component
 */
@Composable
fun SpringModeToggle(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.spring_mode),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium
            ),
            color = MaterialTheme.colorScheme.onSurface
        )
        
        Switcher(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled
        )
    }
}

@Preview(showBackground = true, name = "Enabled - ON")
@Composable
fun SpringModeToggleEnabledOnPreview() {
    SpringValidationTheme {
        var checked by remember { mutableStateOf(true) }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            SpringModeToggle(
                checked = checked,
                onCheckedChange = { checked = it },
                enabled = true
            )
        }
    }
}

@Preview(showBackground = true, name = "Enabled - OFF")
@Composable
fun SpringModeToggleEnabledOffPreview() {
    SpringValidationTheme {
        var checked by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            SpringModeToggle(
                checked = checked,
                onCheckedChange = { checked = it },
                enabled = true
            )
        }
    }
}

@Preview(showBackground = true, name = "Disabled - OFF")
@Composable
fun SpringModeToggleDisabledOffPreview() {
    SpringValidationTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            SpringModeToggle(
                checked = false,
                onCheckedChange = { },
                enabled = false
            )
        }
    }
}
