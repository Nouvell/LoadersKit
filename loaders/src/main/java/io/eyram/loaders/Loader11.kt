package io.eyram.loaders

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import io.eyram.loaders.color.LoaderColor
import io.eyram.loaders.util.Dimens
import io.eyram.loaders.util.calculatePlotPoint
import io.eyram.loaders.util.calculatePlotPoints

@Composable
fun Loader11(
    modifier: Modifier = Modifier,
    color: LoaderColor = LoaderColor.Rainbow
) {
    val colorList = remember(color) { color.getColors() }
    val transition = rememberInfiniteTransition()

    val radiusScale by transition.animateFloat(
        initialValue = 0F,
        targetValue = 1F,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = animationTime
                delayMillis = 600
                1F atFraction 0.25F with LinearEasing
                1F atFraction 0.75F with LinearEasing
                0F atFraction 1F
            },
        )
    )

    val rotation by transition.animateFloat(
        initialValue = 0F,
        targetValue = 360F,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = animationTime
                delayMillis = 600
                120F atFraction 0.25F with LinearEasing
                240F atFraction 0.50F with LinearEasing
                360F atFraction 1F
            },
        )
    )

    val particleColor by transition.animateColor(
        initialValue = colorList.first(),
        targetValue = colorList.last(),
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = animationTime
                colorList.forEachIndexed { index, color ->
                    color atFraction ((index + 1).toFloat() / colorList.size)
                }
            },
        ),
        label = "ColorAnimation"
    )

    Canvas(
        modifier = Modifier
            .size(Dimens.DEFAULT_LOADER_SIZE)
    ) {
        val circleRadius = size.minDimension / 2
        val particleRadius = circleRadius / 8

        rotate(rotation) {
            calculatePlotPoints(
                origin = center,
                orbitalRadius = circleRadius * radiusScale,
                numberOfSides = 8
            ).forEach {
                drawCircle(
                    color = particleColor,
                    radius = particleRadius,
                    center = it
                )
            }
        }
    }
}