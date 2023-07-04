package io.eyram.loaders

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import io.eyram.loaders.color.LoaderColor
import io.eyram.loaders.util.Dimens

@Composable
fun Loader02(
    modifier: Modifier = Modifier,
    color: LoaderColor = LoaderColor.Rainbow,
) {

    val colorList = remember(color) { color.getColors() }
    val transition = rememberInfiniteTransition("${TAG}InfiniteTransition")

    val particleRotation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 47f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = ANIMATION_TIME
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
                durationMillis = ANIMATION_TIME / 2
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
                durationMillis = ANIMATION_TIME
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
                durationMillis = ANIMATION_TIME
                colorList.forEachIndexed { index, color ->
                    color atFraction ((index + 1).toFloat() / colorList.size)
                }
            },
        ),
        label = "ColorAnimation"
    )

    val particleOffsetList = remember {
        val spacing = 0.001f
        val sizeAndSpacing = PARTICLE_SIZE_VALUE + spacing

        listOf(
            Offset(-sizeAndSpacing, -sizeAndSpacing),
            Offset(spacing, -sizeAndSpacing),
            Offset(spacing, spacing),
            Offset(-sizeAndSpacing, spacing),
        )
    }

    Canvas(
        modifier = modifier
            .size(Dimens.DEFAULT_LOADER_SIZE)
            .background(Color.LightGray)
    ) {
        val canvasWidth1 = this.size.width
        val canvasHeight1 = this.size.height
        val innerTangentCircleRadius1 = (minOf(canvasWidth1, canvasHeight1) / 2f) - 0f//PARTICLE_SIZE_VALUE

        drawCircle(
            brush = SolidColor(Color.Gray),
            radius = innerTangentCircleRadius1,
        )

        rotate(0f) {
//        rotate(canvasRotation) {

            val particleOffset = 0f//(innerTangentCircleRadius2 * particleOffsetFraction)

            translate(center.x, center.y) {
                val canvasWidth2 = this.size.width
                val canvasHeight2 = this.size.height
                val innerTangentCircleRadius2 = (minOf(canvasWidth2, canvasHeight2) / 2f) - 0f//PARTICLE_SIZE_VALUE

                drawCircle(
                    brush = SolidColor(Color.Magenta),
                    radius = innerTangentCircleRadius2,
                )

                particleOffsetList.forEachIndexed { _, offset ->
                    val topLeft = offset.addShift(particleOffset)
                    val particleCenter = topLeft + CENTER_OFFSET

                    rotate(0f, particleCenter) {
                        drawRect(
                            brush = SolidColor(particleColor),
                            topLeft = offset.addShift(particleOffset),
                            size = PARTICLE_SIZE
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

private const val ANIMATION_TIME = 1_800
private const val PARTICLE_SIZE_VALUE = 40f
private val PARTICLE_SIZE = Size(PARTICLE_SIZE_VALUE, PARTICLE_SIZE_VALUE)
private val CENTER_OFFSET = Offset(PARTICLE_SIZE_VALUE, PARTICLE_SIZE_VALUE) / 2f

private const val TAG = "Loader02"