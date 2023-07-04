package io.nouvell.loaders

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.EaseInOutBack
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Stroke
import io.nouvell.loaders.color.LoaderColor
import io.nouvell.loaders.util.Dimens.DEFAULT_LOADER_SIZE

@Composable
fun Loader15(
    modifier: Modifier = Modifier,
    color: LoaderColor = LoaderColor.Rainbow
) {
    val colorList = remember(color) { color.getColors() }
    val transition = rememberInfiniteTransition()

    val particleColor by transition.animateColor(
        initialValue = colorList.first(),
        targetValue = colorList.last(),
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 1_800 * colorList.size
                colorList.forEachIndexed { index, color ->
                    color atFraction ((index + 1).toFloat() / colorList.size)
                }
            },
        ),
        label = "ColorAnimation"
    )

    val strokeScale by transition.animateFloat(
        initialValue = 1F,
        targetValue = 3F,
        animationSpec = infiniteRepeatable(
            keyframes {
                durationMillis = 1_800
                1F atFraction 0F with EaseInOutBack
                1.67F atFraction 0.38F with EaseInOutBack
                3F atFraction 0.76F
                3F atFraction 1F
            }
        )
    )

    val strokeAlpha by transition.animateFloat(
        initialValue = 1F,
        targetValue = 0F,
        animationSpec = infiniteRepeatable(
            keyframes {
                durationMillis = 1_800
                1F atFraction 0.38F with EaseInOut
                0F atFraction 0.76F
                0F atFraction 1F
            }
        )
    )

    val circleScale by transition.animateFloat(
        initialValue = 1F,
        targetValue = 1.5F,
        animationSpec = infiniteRepeatable(
            keyframes {
                durationMillis = 1_800
                1F atFraction 0F with EaseInOutBack
                1.5F atFraction 0.38F with EaseInOutBack
                1F atFraction 0.76F with EaseInOutBack
                1F atFraction 1F
            }
        )
    )

    Canvas(modifier = modifier.size(DEFAULT_LOADER_SIZE)) {

        val strokeWidth = size.minDimension * 0.0625F
        val strokeRadius = (size.minDimension / 2) * 0.1875F

        val circleRadius = (size.minDimension / 2) * 0.25F
        drawCircle(
            radius = strokeRadius * strokeScale,
            color = particleColor,
            style = Stroke(strokeWidth),
            alpha = strokeAlpha
        )

        drawCircle(
            radius = circleRadius * circleScale,
            color = particleColor,
        )
    }
}