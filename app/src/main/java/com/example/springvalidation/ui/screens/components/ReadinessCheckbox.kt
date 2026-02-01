package com.example.springvalidation.ui.screens.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.springvalidation.R
import com.example.springvalidation.ui.common_components.ContentCard
import com.example.springvalidation.ui.theme.SpringValidationTheme

/**
 * Confirmation checkbox for readiness to proceed.
 * Displays a labeled checkbox that users must check to confirm their readiness to move forward.
 *
 * @param checked Current checkbox state (true if checked)
 * @param onCheckedChange Callback invoked when checkbox state changes
 * @param modifier Optional modifier
 */
@Composable
fun ReadinessCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    ContentCard(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = (-12).dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    uncheckedColor = MaterialTheme.colorScheme.outline
                )
            )
            Text(
                text = stringResource(R.string.ready_to_move_forward),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }

}

@Preview(
    showBackground = true
)
@Composable
fun ReadinessCheckboxPreview() {
    SpringValidationTheme {
        ReadinessCheckbox(
            checked = true,
            onCheckedChange = {}
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ReadinessCheckboxUncheckedPreview() {
    SpringValidationTheme {
        ReadinessCheckbox(
            checked = false,
            onCheckedChange = {}
        )
    }
}
