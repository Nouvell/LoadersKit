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
import io.nouvell.loaders.color.LoaderColor
import io.nouvell.loaders.util.Dimens
import io.nouvell.loaders.util.calculatePlotPoint

@Composable
fun Loader04(
    modifier: Modifier = Modifier,
    color: LoaderColor = LoaderColor.Rainbow,
) {
    val colorList = remember(color) { color.getColors() }
    val transition = rememberInfiniteTransition("${TAG}InfiniteTransition")

    val initDegreeAngle = List(5) { 270F }
    val animatedValues = initDegreeAngle.mapIndexed { index, initPosition ->
        val secPosition = initPosition - (index * 22.5F)
        transition.animateValue(
            initialValue = initPosition,
            targetValue = 90F,
            typeConverter = Float.VectorConverter,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = ANIMATION_TIME
                    initPosition atFraction 0F with LinearEasing
                    secPosition atFraction 0.23F with LinearEasing
                    secPosition - 120 atFraction 0.45F with EaseOut
                    initPosition - 360 atFraction 1F
                },
            )
        )
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
        val outerRadius = size.width / 2
        val innerRadius = outerRadius * 0.625F

        animatedValues.forEach {
            drawLine(
                color = particleColor,
                strokeWidth = size.width * 0.0625F,
                start = calculatePlotPoint(center, innerRadius, it.value),
                end = calculatePlotPoint(center, outerRadius, it.value)
            )
        }
    }
}

private const val TAG = "Loader04"
private const val ANIMATION_TIME = 1_250