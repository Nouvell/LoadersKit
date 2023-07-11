package io.nouvell.loaders

import androidx.compose.animation.animateColor
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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.lerp
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import io.nouvell.loaders.color.LoaderColor
import io.nouvell.loaders.util.Dimens.DEFAULT_LOADER_SIZE

@Composable
fun Loader14(
    modifier: Modifier = Modifier,
    color: LoaderColor = LoaderColor.Rainbow,
) {
    val colorList = remember(color) { color.getColors() }
    val transition = rememberInfiniteTransition("${TAG}InfiniteTransition")

    val translationX by transition.animateFloat(
        initialValue = 0F,
        targetValue = 1F,
        animationSpec = infiniteRepeatable(
            keyframes {
                durationMillis = ANIMATION_TIME
                .25F atFraction 0.1875F
                1F atFraction 0.375F
                1F atFraction 1F
            },
        )
    )

    val sizeFraction by transition.animateFloat(
        initialValue = 0F,
        targetValue = 1F,
        animationSpec = infiniteRepeatable(
            keyframes {
                durationMillis = ANIMATION_TIME
                1F atFraction 0.1875F
                1F atFraction 1F
            }
        )
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
            .size(DEFAULT_LOADER_SIZE)
    ) {
        val initParticleSize = size * 0.25F
        val finalParticleSize = Size(initParticleSize.width * 1.5F, initParticleSize.height * 0.5F)
        val particleSize = lerp(initParticleSize, finalParticleSize, sizeFraction)

        val boundingBoxStrokeWidth = size.minDimension * 0.0625F
        val boundingBoxSize = Size(size.width - (boundingBoxStrokeWidth), size.height * 0.3125F)

        val initParticleOffsetX = (size.minDimension * 0.25F) + boundingBoxStrokeWidth
        val particleOffsetY = (size.height / 2) - (particleSize.height / 2)


        clipRect(boundingBoxStrokeWidth / 2) {
            drawRoundRect(
                topLeft = lerp(
                    Offset(-initParticleOffsetX, particleOffsetY),
                    Offset(size.width, particleOffsetY),
                    translationX
                ),
                color = particleColor,
                size = particleSize,
                cornerRadius = CornerRadius(particleSize.height * 0.50F)
            )
        }

        drawRoundRect(
            color = particleColor,
            topLeft = Offset(
                boundingBoxStrokeWidth / 2,
                (size.height / 2) - (boundingBoxSize.height / 2)
            ),
            size = boundingBoxSize,
            cornerRadius = CornerRadius(boundingBoxSize.height * 0.5F),
            style = Stroke(width = boundingBoxStrokeWidth)
        )
    }
}

private const val TAG = "Loader14"
private const val ANIMATION_TIME = 1_000