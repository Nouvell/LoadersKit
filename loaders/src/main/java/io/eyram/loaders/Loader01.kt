package io.eyram.loaders

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.eyram.loaders.color.LoaderColor
import io.eyram.loaders.util.Dimens

@Composable
fun Loader01(
    modifier: Modifier = Modifier,
    color: LoaderColor = LoaderColor.Rainbow,
    strokeWidth: Dp = 8.dp,
) {
    val colorList = remember(color) { color.getColors() }
    val transition = rememberInfiniteTransition("${TAG}InfiniteTransition")

    val startAngle = transition.animateFloat(
        initialValue = 0F,
        targetValue = 360F,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = animationTime
                120F atFraction 0.25F with LinearEasing
                240F atFraction 0.50F with EaseOut
                360F atFraction 1F
            }
        )
    )

    val sweepAngle = transition.animateFloat(
        initialValue = 18F,
        targetValue = 72F,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = animationTime
                72F atFraction 0.25F with LinearEasing
                72F atFraction 0.50F with EaseOut
                18F atFraction 1F
            }
        )
    )

    val arcColor by transition.animateColor(
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
        val arcSize = size.minDimension - strokeWidth.toPx()
        val arcOffset = strokeWidth.toPx() / 2

        drawArc(
            color = arcColor,
            useCenter = false,
            startAngle = startAngle.value,
            sweepAngle = sweepAngle.value,
            size = Size(arcSize, arcSize),
            topLeft = Offset(arcOffset, arcOffset),
            style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
        )
    }
}


private const val TAG = "Loader01"