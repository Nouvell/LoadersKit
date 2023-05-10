package io.eyram.loaders

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.defaultMinSize
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
fun Arc(
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    startAngle: Float = 0F,
    sweepAngle: Float = 18F,
    strokeWidth: Float = 20F,
    size: Dp = DEFAULT_MIN_ARC_SIZE
) {
    val sizeInFloat = with(LocalDensity.current) {
        size.toPx() - strokeWidth
    }

    Canvas(modifier = modifier.size(size)) {
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

@Composable
fun CircularLoadingAnimation() {
    val startAngle = remember { Animatable(0F) }
    val sweepAngle = remember { Animatable(18F) }

    /** Thinking of exposing the size as a dp to the user and I can just convert them to float myself*/
    Arc(startAngle = startAngle.value, sweepAngle = sweepAngle.value)

    LaunchedEffect(Unit) {
        fun tweenSpec(durationMillis: Int = 250) =
            tween<Float>(durationMillis = durationMillis, delayMillis = 1, LinearEasing)

        /**Using DelayMillis makes the animation lose sync (see)[https://touchlab.co/jetpack-compose-animations-state-change/]*/
        launch {
            while (true) {
                startAngle.apply {
                    animateTo(targetValue = 240F, animationSpec = tweenSpec(500))
                    animateTo(targetValue = 360F, animationSpec = tweenSpec(500))
                    snapTo(0F)
                }
            }
        }

        launch {
            while (true) {
                sweepAngle.animateTo(targetValue = 72F, animationSpec = tweenSpec())
                delay(250)
                sweepAngle.animateTo(targetValue = 18F, animationSpec = tweenSpec(500))
            }
        }
    }
}


val DEFAULT_MIN_ARC_SIZE = 200.dp