package io.nouvell.loaders

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import io.nouvell.loaders.color.LoaderColor
import io.nouvell.loaders.util.Dimens
import io.nouvell.loaders.util.calculatePlotPoint

@Composable
fun Loader17(
    modifier: Modifier = Modifier,
    color: LoaderColor = LoaderColor.Rainbow,
) {
    val colorList = remember(color) { color.getColors() }
    val transition = rememberInfiniteTransition("${TAG}InfiniteTransition")

    val animatedAnglesOfRotation = listOf(300F, 320F, 340F, 362F, 390F).map { initAngle ->
        val animatedRotation = transition.animateValue(
            initialValue = 180F,
            targetValue = 540F,
            typeConverter = Float.VectorConverter,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = ANIMATION_TIME
                    initAngle atFraction 0.25F with LinearEasing
                    initAngle + 120F atFraction 0.50F with EaseOut
                    540F atFraction 1F
                },
                repeatMode = RepeatMode.Restart
            ),
        )
        animatedRotation.value
    }

    val animatedAnglesOfRotation2 = listOf(120F, 140F, 160F, 182F, 210F).map { initAngle ->
        val animatedRotation = transition.animateValue(
            initialValue = 0F,
            targetValue = 360F,
            typeConverter = Float.VectorConverter,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = ANIMATION_TIME
                    initAngle atFraction 0.25F with LinearEasing
                    initAngle + 120F atFraction 0.50F with EaseOut
                    360F atFraction 1F
                },
                repeatMode = RepeatMode.Restart
            ),
        )
        animatedRotation.value
    }

    val particleColor by transition.animateColor(
        initialValue = colorList.first(),
        targetValue = colorList.last(),
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = ANIMATION_TIME
                colorList.forEachIndexed { index, color ->
                    color atFraction ((index + 1).toFloat() / colorList.size)
                }
            },
        ),
        label = "ColorAnimation"
    )

    Canvas(
        modifier = modifier
            .size(Dimens.DEFAULT_LOADER_SIZE)
    ) {
        val canvasRadius = size.minDimension / 2

        animatedAnglesOfRotation.forEachIndexed { idx, angle ->
            val index = idx + 1
            drawCircle(
                color = particleColor,
                radius = (index  * canvasRadius) / (32f - index),
                center = calculatePlotPoint(center, canvasRadius, angle)
            )
        }

        animatedAnglesOfRotation2.forEachIndexed { idx, angle ->
            val index = idx + 1
            drawCircle(
                color = particleColor,
                radius = (index  * canvasRadius) / (32f - index),
                center = calculatePlotPoint(center, canvasRadius, angle)
            )
        }
    }
}

private const val TAG = "Loader17"
private const val ANIMATION_TIME = 1_500