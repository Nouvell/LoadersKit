package io.nouvell.loaders

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import io.nouvell.loaders.color.LoaderColor
import io.nouvell.loaders.util.ANIMATION_TIME
import io.nouvell.loaders.util.Dimens.DEFAULT_LOADER_SIZE

@Composable
fun Loader18(
    modifier: Modifier = Modifier,
    color: LoaderColor = LoaderColor.White
) {
    val transition = rememberInfiniteTransition("InfiniteTransition")

    val startAngle by transition.animateFloat(
        initialValue = 0F,
        targetValue = 360F,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = ANIMATION_TIME
                120F atFraction 0.25F with LinearEasing
                240F atFraction 0.50F with EaseOut
                360F atFraction 1F
            }
        )
    )

    Canvas(
        modifier = modifier
            .graphicsLayer {
                clip = false
                this.cameraDistance = 20F
            }
            .size(DEFAULT_LOADER_SIZE)
    ) {

        val strokeWidth = size.minDimension / 32

        val dash = strokeWidth / 2
        val gap = 4 * dash


        repeat(3) { index ->
            val arcSize = size * (1 - (index * 0.03125F))
            val offsetY = (((index + 1) * 0.5) / 32F) * size.height

            drawArc(
                useCenter = false,
                startAngle = 0F,
                sweepAngle = 360F,
                topLeft = Offset(0F, offsetY.toFloat()),
                size = arcSize,
                color = Color.White,
                style = Stroke(
                    width = strokeWidth,
                    pathEffect = PathEffect.dashPathEffect(
                        floatArrayOf(dash, gap)
                    )
                )
            )
        }


        val path = Path()

        drawArc(
            color = Color.Yellow,
            useCenter = false,
            startAngle = startAngle,
            sweepAngle = 108F,
            size = size,
            style = Stroke(
                width = 8 / 32F * size.minDimension,
                cap = StrokeCap.Round
            )
        )


        val offsetY = 2 / 32F * size.height
        drawArc(
            color = Color.Black,
            topLeft = Offset(0F, offsetY),
            useCenter = false,
            startAngle = startAngle,
            sweepAngle = 360F,
            size = size * 0.925F,
            blendMode = BlendMode.Clear
        )
    }
}
