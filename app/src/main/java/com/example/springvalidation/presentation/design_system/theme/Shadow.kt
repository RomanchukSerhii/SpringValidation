package com.example.springvalidation.presentation.design_system.theme

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Applies a custom shadow to a composable using canvas drawing.
 * Creates a more visible and controllable shadow with offset and blur.
 *
 * @param blurRadius The blur radius of the shadow (default: 16dp)
 * @param cornerRadius The corner radius of the shadow shape (default: 16dp)
 * @param positionX Horizontal offset of the shadow (default: 0dp)
 * @param positionY Vertical offset of the shadow (default: 4dp)
 * @param shadowColor The base color of the shadow (default: dark gray)
 * @param shadowAlpha The opacity of the shadow (default: 0.1 = 10%)
 */
fun Modifier.cardShadow(
    blurRadius: Dp = 16.dp,
    cornerRadius: Dp = 16.dp,
    positionX: Dp = 0.dp,
    positionY: Dp = 4.dp,
    shadowColor: Color = Color(0xFF202306),
    shadowAlpha: Float = 0.1f
): Modifier {
    return drawBehind {
        drawIntoCanvas { canvas ->
            val shadowColorWithAlpha = shadowColor.copy(alpha = shadowAlpha)
            val paint = Paint().apply {
                color = shadowColorWithAlpha
                isAntiAlias = true
            }

            paint.asFrameworkPaint()
                .setShadowLayer(
                    blurRadius.toPx(),
                    positionX.toPx(),
                    positionY.toPx(),
                    shadowColorWithAlpha.toArgb()
                )

            canvas.drawRoundRect(
                left = 0f,
                top = 0f,
                right = size.width,
                bottom = size.height,
                radiusX = cornerRadius.toPx(),
                radiusY = cornerRadius.toPx(),
                paint = paint
            )
        }
    }
}
