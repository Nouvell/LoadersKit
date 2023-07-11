package io.nouvell.loaders

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
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
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import io.nouvell.loaders.color.LoaderColor
import io.nouvell.loaders.util.Dimens
import io.nouvell.loaders.util.calculatePlotPoint

@Composable
fun Loader10(
    modifier: Modifier = Modifier,
    color: LoaderColor = LoaderColor.Rainbow,
) {
    val colorList = remember(color) { color.getColors() }
    val transition = rememberInfiniteTransition("${TAG}InfiniteTransition")

    val electronOnePositionDegrees by transition.animateValue(
        initialValue = 180F,
        targetValue = 540F,
        typeConverter = Float.VectorConverter,
        animationSpec = infiniteRepeatable(
            keyframes {
                durationMillis = ANIMATION_TIME
                300F atFraction 0.25F with LinearEasing
                420F atFraction 0.50F with EaseOut
                540F atFraction 1F
            },
        ),
    )

    val electronTwoPositionDegrees by transition.animateValue(
        initialValue = 0F,
        targetValue = 360F,
        typeConverter = Float.VectorConverter,
        animationSpec = infiniteRepeatable(
            keyframes {
                durationMillis = ANIMATION_TIME
                120F atFraction 0.25F with LinearEasing
                240F atFraction 0.50F with EaseOut
                360F atFraction 1F
            },
        ),
    )

    val orbitalRotation by transition.animateValue(
        initialValue = 180F,
        targetValue = 540F,
        typeConverter = Float.VectorConverter,
        animationSpec = infiniteRepeatable(
            keyframes {
                durationMillis = ANIMATION_TIME
                210F atFraction 0.25F with LinearEasing
                330F atFraction 0.50F with EaseOut
                540F atFraction 1F
            },
        ),
    )

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
        val dashCircleRadius = size.minDimension / 2
        val particleRadius = dashCircleRadius / 8
        val strokeWidth = size.minDimension / 32

        val dash = strokeWidth
        val gap = 4 * dash

        rotate(orbitalRotation) {
            drawCircle(
                color = particleColor,
                radius = dashCircleRadius,
                style = Stroke(
                    width = strokeWidth, cap = StrokeCap.Round,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(dash, gap), 0F)
                )
            )
            drawCircle(
                color = particleColor,
                radius = particleRadius,
                center = calculatePlotPoint(center, dashCircleRadius, 0F)
            )

            drawCircle(
                color = particleColor,
                radius = particleRadius,
                center = calculatePlotPoint(center, dashCircleRadius, 180F)
            )
        }

        //nucleus
        drawCircle(color = particleColor, radius = particleRadius)

        drawCircle(
            color = particleColor,
            radius = particleRadius,
            center = calculatePlotPoint(center, dashCircleRadius, electronTwoPositionDegrees)
        )

        drawCircle(
            color = particleColor,
            radius = particleRadius,
            center = calculatePlotPoint(center, dashCircleRadius, electronOnePositionDegrees)
        )
    }
}


private const val TAG = "Loader10"
private const val ANIMATION_TIME = 1_100