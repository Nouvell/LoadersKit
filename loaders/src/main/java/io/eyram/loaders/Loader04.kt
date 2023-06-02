package io.eyram.loaders

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.eyram.loaders.color.LoaderColor
import io.eyram.loaders.util.Dimens
import io.eyram.loaders.util.calculatePlotPoint

@Composable
fun Loader04(
    modifier: Modifier = Modifier,
    color: LoaderColor = LoaderColor.Rainbow,
) {

    val colorList = remember(color) { color.getColors() }
    val transition = rememberInfiniteTransition()

    val initDegreeAngle = List(5) { 270F }

    val animatedValues = initDegreeAngle.mapIndexed { index, initPosition ->
        val secPosition = initPosition - (index * 22.5F)
        transition.animateValue(
            initialValue = initPosition,
            targetValue = 90F,
            typeConverter = Float.VectorConverter,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = animationTime
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
                durationMillis = animationTime * colorList.size
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