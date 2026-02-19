package com.example.springvalidation.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.springvalidation.R
import com.example.springvalidation.ui.theme.SpringValidationTheme
import kotlin.math.roundToInt

/**
 * Confidence level slider component.
 * Displays a slider with value from 1 to 5 and a visual indicator above it.
 *
 * @param value Current confidence level (1-5)
 * @param onValueChange Callback when value changes
 * @param modifier Optional modifier
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfidenceSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        // Label
        Text(
            text = stringResource(R.string.confidence_level),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium
            ),
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 2.dp)
        )

        // Slider with value indicator using Layout for precise positioning
        Layout(
            content = {
                // Indicator circle (will be positioned above thumb)
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(
                            color = MaterialTheme.colorScheme.secondary,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = value.toInt().toString(),
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                }
                
                // Default slider
                Slider(
                    value = value,
                    onValueChange = onValueChange,
                    valueRange = 1f..5f,
                    steps = 3, // 5 values: 1, 2, 3, 4, 5
                    modifier = Modifier.fillMaxWidth(),
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.primary,
                        activeTrackColor = MaterialTheme.colorScheme.primary,
                        inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer
                    ),
                    track = { sliderState ->
                        SliderDefaults.Track(
                            sliderState = sliderState,
                            modifier = Modifier.height(12.dp)
                        )
                    },
                    thumb = {
                        Box(
                            modifier = Modifier
                                .width(4.dp)
                                .height(36.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = CircleShape
                                )
                        )
                    }
                )
            }
        ) { measurables, constraints ->
            // Measure slider first
            val sliderPlaceable = measurables[1].measure(constraints)
            
            // Measure indicator
            val indicatorPlaceable = measurables[0].measure(constraints)
            
            // Calculate slider track position (approximate)
            val sliderTrackStart = 6 // Approximate padding from slider start
            val sliderTrackWidth = sliderPlaceable.width - sliderTrackStart * 2
            val fraction = (value - 1f) / 4f // 0-1 range
            
            // Position indicator above thumb with minimal spacing
            val indicatorX = (sliderTrackStart + sliderTrackWidth * fraction - indicatorPlaceable.width / 2).roundToInt()
            val indicatorY = 0
            
            // Total height = indicator + minimal spacing + slider
            val totalHeight = indicatorPlaceable.height + sliderPlaceable.height
            
            layout(sliderPlaceable.width, totalHeight) {
                // Place indicator at top
                indicatorPlaceable.place(indicatorX, indicatorY)
                
                // Place slider below indicator with minimal spacing
                sliderPlaceable.place(0, indicatorPlaceable.height)
            }
        }
        
        // Min/max labels under the slider
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.low),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = stringResource(R.string.high),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 200,
    locale = "en"
)
@Composable
fun ConfidenceSliderPreview() {
    SpringValidationTheme {
        var confidenceLevel by remember { mutableFloatStateOf(3f) }
        
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp)
        ) {
            ConfidenceSlider(
                value = confidenceLevel,
                onValueChange = { confidenceLevel = it }
            )
        }
    }
}
