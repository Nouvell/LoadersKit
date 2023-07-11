package io.nouvell.loaders

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.EaseInOut
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
import androidx.compose.ui.geometry.lerp
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import io.nouvell.loaders.color.LoaderColor
import io.nouvell.loaders.util.Dimens.DEFAULT_LOADER_SIZE
import io.nouvell.loaders.util.calculatePlotPoints

@Composable
fun Loader08(
    modifier: Modifier = Modifier,
    color: LoaderColor = LoaderColor.Rainbow
) {
    val colorList = remember(color) { color.getColors() }
    val transition = rememberInfiniteTransition("${TAG}InfiniteTransition")

    val particleOffsetFraction by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = ANIMATION_TIME
                1F atFraction 0.27F with EaseInOut
                0F atFraction 1F
            },
        ),
        label = "ParticleOffset"
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

    Canvas(modifier = modifier.size(DEFAULT_LOADER_SIZE)) {
        val orbitalRadius = size.minDimension / 2
        val diskRadius = orbitalRadius * 0.1875F

        val particleOffsets = calculatePlotPoints(
            origin = center,
            orbitalRadius = orbitalRadius * 0.5625F,
            numberOfSides = 3,
        )

        rotate(-30F) {//original design starts at 90F, using calcPlotPoints, it starts at 120F
            particleOffsets.forEachIndexed { index, offset ->
                drawCircle(
                    color = particleColor,
                    radius = diskRadius,
                    center = lerp(
                        start = offset,
                        stop = particleOffsets[(index + 1) % 3],
                        fraction = particleOffsetFraction
                    ),
                    style = Stroke(width = size.minDimension * 0.0625F)
                )
            }
        }
    }
}

private const val TAG = "Loader08"
private const val ANIMATION_TIME = 1_800