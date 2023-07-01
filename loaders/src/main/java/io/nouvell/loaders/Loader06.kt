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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import io.nouvell.loaders.color.LoaderColor
import io.nouvell.loaders.util.Dimens.DEFAULT_LOADER_SIZE

@Composable
fun Loader06(
    modifier: Modifier = Modifier,
    color: LoaderColor = LoaderColor.Rainbow,
) {
    val colorList = remember(color) { color.getColors() }
    val transition = rememberInfiniteTransition()

    val orbitalRotation by transition.animateValue(
        initialValue = 0F,
        targetValue = 360F,
        typeConverter = Float.VectorConverter,
        animationSpec = infiniteRepeatable(
            keyframes {
                durationMillis = animationTime
                120F atFraction .25F with LinearEasing
                240F atFraction .50F with EaseOut
                360F atFraction 1F
            },
        ),
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


    Canvas(modifier = modifier
        .size(DEFAULT_LOADER_SIZE)
        .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
    ) {
        val dashCircleRadius = (size.minDimension / 2) * 0.875F
        val nucleusRadius = dashCircleRadius / 8
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

            drawRoundRect(
                color = particleColor,
                size = Size(size.width, nucleusRadius),
                topLeft = Offset(0F, center.y - (nucleusRadius / 2)),
                cornerRadius = CornerRadius(nucleusRadius / 2),
            )

            drawCircle(
                color = Color.Unspecified,
                radius = dashCircleRadius / 1.75F,
                blendMode = BlendMode.Clear
            )
        }

        //nucleus
        drawCircle(color = particleColor, radius = nucleusRadius)
    }
}
