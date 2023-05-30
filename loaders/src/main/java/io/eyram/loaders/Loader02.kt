package io.eyram.loaders

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.RepeatMode
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import io.eyram.loaders.color.LoaderColor
import io.eyram.loaders.util.Dimens

const val animationTime = 1_800

@Composable
fun Loader02(
    modifier: Modifier = Modifier,
    color: LoaderColor = LoaderColor.Rainbow,
) {

    val colorList = remember(color) { color.getColors() }
    val transition = rememberInfiniteTransition("${TAG}InfiniteTransition")

    val particleSize = remember { 40f }

    val particleRotation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 47f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = animationTime
                0f atFraction 0.35f
                47f atFraction 0.65f
                90f atFraction 1f
            }
        ),
        label = "ParticleRotation"
    )

    val particleOffsetFraction by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = animationTime / 2
                0f atFraction 0f with EaseOut
                0.8f atFraction 0.65f
                1f atFraction 1f
            },
            repeatMode = RepeatMode.Reverse,
        ),
        label = "ParticleOffset"
    )


    val canvasRotation by transition.animateFloat(
        initialValue = 45f,
        targetValue = 405f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = animationTime
                45f atFraction 0f with EaseOut
                135f atFraction 0.35f with EaseOut
                315f atFraction 0.65f
                405f atFraction 1f
            },
        ),
        label = "CanvasRotation"
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

    val particleOffsetList = remember {
        val spacing = 0.001f

        listOf(
            Offset(-(particleSize + spacing), -(particleSize + spacing)),
            Offset(spacing, -(particleSize + spacing)),
            Offset(spacing, spacing),
            Offset(-(particleSize + spacing), spacing),
        )
    }

    Canvas(
        modifier = modifier
            .size(Dimens.DEFAULT_LOADER_SIZE)
//            .background(Color.LightGray)
    ) {
//        val particleOffset = center.y - particleSize//abs((center.y - particleSize) * particleOffsetFraction)

//        Log.i(TAG, "Center Y: ${center.y}")
//        Log.i(TAG, "Particle Offset Fraction: $particleOffsetFraction")

//        drawRect(
//            brush = SolidColor(Color.Green),
//            topLeft = Offset(center.x, center.y - 20f),
//            size = Size(20f, 20f)
//        )

        rotate(canvasRotation) {
            val particleOffset = 50f * particleOffsetFraction

            translate(center.x, center.y) {
                particleOffsetList.forEachIndexed { _, offset ->
                    val topLeft = offset.addShift(particleOffset)
                    val particleCenter = topLeft + (Offset(particleSize, particleSize) / 2f)

                    rotate(particleRotation, particleCenter) {
                        drawRect(
                            brush = SolidColor(particleColor),
                            topLeft = offset.addShift(particleOffset),
                            size = Size(particleSize, particleSize)
                        )
                    }
                }
            }
        }
    }
}

private fun Offset.addShift(shift: Float): Offset {
    val newX = if (x > 0) x + shift else x - shift
    val newY = if (y > 0) y + shift else y - shift

    return Offset(newX, newY)
}

private const val TAG = "Loader02"