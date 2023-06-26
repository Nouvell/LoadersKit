package io.eyram.loaders

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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.lerp
import androidx.compose.ui.unit.dp
import io.eyram.loaders.color.LoaderColor
import io.eyram.loaders.util.Dimens.DEFAULT_LOADER_SIZE
import io.eyram.loaders.util.lerp

@Composable
fun Loader12(
    modifier: Modifier = Modifier,
    color: LoaderColor = LoaderColor.Rainbow,
) {
    val colorList = remember(color) { color.getColors() }
    val transition = rememberInfiniteTransition()

    val particleOffsetFraction by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = animationTime
                0F atFraction 0.11F with EaseInOut
                1F atFraction 1F
            },
        ),
        label = "ParticleOffset"
    )

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

    Canvas(modifier = modifier.size(DEFAULT_LOADER_SIZE)) {
        val particleOffsets = listOf(
            Offset(0F, size.height / 2),
            Offset(size.width / 2, size.height / 2),
            Offset(size.width, size.height / 2),
        )

        val minParticleRadius = (1 / 8F * size.minDimension) / 2
        val maxParticleRadius = 3 * minParticleRadius
        val particleRadii = listOf(minParticleRadius, maxParticleRadius, minParticleRadius)

        particleOffsets.forEachIndexed { index, offset ->
            drawCircle(
                color = particleColor,
                radius = lerp(
                    min = particleRadii[index],
                    max = particleRadii[(index + 1) % 3],
                    fraction = particleOffsetFraction
                ),
                center = lerp(
                    start = offset,
                    stop = particleOffsets[(index + 1) % 3],
                    fraction = particleOffsetFraction
                )
            )
        }
    }
}
