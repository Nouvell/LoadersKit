package io.eyram.loaders

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CircularLoadingAnimation(
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    size: Dp = DEFAULT_MIN_ARC_SIZE,
    strokeWidth: Float = 20F,
) {
    val startAngle = remember { Animatable(0F) }
    val sweepAngle = remember { Animatable(18F) }

    /** Thinking of exposing the size as a dp to the user and I can just convert them to float myself*/
    /*Try exposing the stroke cap as well*/
    Arc(
        size = size,
        color = color,
        strokeWidth = strokeWidth,
        startAngle = startAngle.value,
        sweepAngle = sweepAngle.value,
    )

    LaunchedEffect(Unit) {

        launch {
            startAngle.animateTo(
                targetValue = 0F,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = 1004
                        0F at 1
                        (-120F).toComposeRotation() at 251
                        (-120F).toComposeRotation() at 252
                        (120F).toComposeRotation() at 502
                        (120F).toComposeRotation() at 503
                        360F at 1003
                        360F at 1004
                    }
                )
            )
        }

        launch {
            sweepAngle.animateTo(targetValue = 72F,
                animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = 1004
                        18F at 1
                        72F at 252
                        72F at 503
                        18F at 1004
                    }
                )
            )
        }
    }
}


@Composable
fun Arc(
    size: Dp,
    color: Color,
    startAngle: Float,
    sweepAngle: Float,
    strokeWidth: Float,
) {
    val sizeInFloat = with(LocalDensity.current) {
        size.toPx() - strokeWidth
    }

    Canvas(modifier = Modifier.size(size)) {
        val canvasSize = this.size
        val offsetX = (canvasSize.width - sizeInFloat) / 2
        val offsetY = (canvasSize.height - sizeInFloat) / 2

        drawArc(
            color = color,
            useCenter = false,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            size = Size(sizeInFloat, sizeInFloat),
            topLeft = Offset(offsetX, offsetY),
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )
    }
}

val DEFAULT_MIN_ARC_SIZE = 200.dp