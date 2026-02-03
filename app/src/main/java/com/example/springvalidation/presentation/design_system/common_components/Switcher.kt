package com.example.springvalidation.presentation.design_system.common_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.springvalidation.presentation.design_system.theme.SpringValidationTheme

/**
 * Custom switcher component with three states: On, Off, and Disabled.
 * Uses Material Design color scheme for consistent theming.
 *
 * @param checked Current state of the switch
 * @param onCheckedChange Callback when switch state changes
 * @param modifier Optional modifier
 * @param enabled Whether the switch is enabled (default: true)
 */
@Composable
fun Switcher(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled,
        colors = SwitchDefaults.colors(
            // ON state - primary colors
            checkedThumbColor = MaterialTheme.colorScheme.onPrimary,
            checkedTrackColor = MaterialTheme.colorScheme.primary,
            checkedBorderColor = Color.Transparent,
            
            // OFF state - surface and outline colors
            uncheckedThumbColor = MaterialTheme.colorScheme.outline,
            uncheckedTrackColor = MaterialTheme.colorScheme.surfaceContainerHighest,
            uncheckedBorderColor = MaterialTheme.colorScheme.outline,
            
            // DISABLED state
            disabledCheckedThumbColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
            disabledCheckedTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
            disabledCheckedBorderColor = Color.Transparent,
            disabledUncheckedThumbColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.32f),
            disabledUncheckedTrackColor = Color.Transparent,
            disabledUncheckedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SwitcherPreview() {
    SpringValidationTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Switcher Component",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // ON state (Spring mode)
            var switcherOnState by remember { mutableStateOf(true) }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Spring mode",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
                Switcher(
                    checked = switcherOnState,
                    onCheckedChange = { switcherOnState = it }
                )
            }

            // OFF state
            var switcherOffState by remember { mutableStateOf(false) }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Spring mode",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
                Switcher(
                    checked = switcherOffState,
                    onCheckedChange = { switcherOffState = it }
                )
            }

            // DISABLED state (ON)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Disabled (ON)",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    ),
                    modifier = Modifier.weight(1f)
                )
                Switcher(
                    checked = true,
                    onCheckedChange = { },
                    enabled = false
                )
            }

            // DISABLED state (OFF)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Disabled (OFF)",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    ),
                    modifier = Modifier.weight(1f)
                )
                Switcher(
                    checked = false,
                    onCheckedChange = { },
                    enabled = false
                )
            }
        }
    }
}
