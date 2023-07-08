package io.nouvell.loaders

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.StartOffsetType
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import io.nouvell.loaders.color.LoaderColor
import io.nouvell.loaders.util.Dimens
import kotlin.math.roundToInt

@Composable
fun Loader07(
    modifier: Modifier = Modifier,
    color: LoaderColor = LoaderColor.Rainbow,
) {
    val colorList = remember(color) { color.getColors() }
    val transition = rememberInfiniteTransition("${TAG}InfiniteTransition")

    val animatedProperties = List(3) { index ->
        val radiusScale by transition.animateFloat(
            initialValue = 0.0625F,
            targetValue = 0.0625F,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = ANIMATION_TIME
                    0.0625F atFraction 0.111F with EaseInOut

                    0.75F atFraction 0.333F
                    0.75F atFraction 0.444F with EaseInOut

                    1F atFraction 0.667F
                    1F atFraction 0.778F with EaseInOut

                    0.0625F atFraction 1F
                },
                initialStartOffset = StartOffset(
                    offsetType = StartOffsetType.FastForward,
                    offsetMillis = (ANIMATION_TIME * index / 3F).roundToInt()
                )
            )
        )

        val alpha by transition.animateFloat(
            initialValue = 0F,
            targetValue = 0F,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = ANIMATION_TIME
                    0F atFraction 0.111F with EaseInOut

                    1F atFraction 0.333F
                    1F atFraction 0.444F with EaseInOut

                    0F atFraction 0.667F
                    0F atFraction 1F
                },
                initialStartOffset = StartOffset(
                    offsetType = StartOffsetType.FastForward,
                    offsetMillis = (ANIMATION_TIME * index / 3F).roundToInt()
                )
            )
        )

        val strokeScale by transition.animateFloat(
            initialValue = 0.0078F,
            targetValue = 0.1250F,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = ANIMATION_TIME
                    0.0078F atFraction 0.111F with EaseInOut

                    0.0625F atFraction 0.333F
                    0.0625F atFraction 0.444F with EaseInOut

                    0.1250F atFraction 0.667F
                    0.1250F atFraction 1F
                },
                initialStartOffset = StartOffset(
                    offsetType = StartOffsetType.FastForward,
                    offsetMillis = (ANIMATION_TIME * index / 3F).roundToInt()
                )
            )
        )

        Triple(radiusScale, alpha, strokeScale)
    }


    val loaderColor by transition.animateColor(
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
            .size(Dimens.DEFAULT_LOADER_SIZE)
    ) {
        animatedProperties.forEach { (radiusScale, alpha, strokeScale) ->
            drawCircle(
                color = loaderColor,
                radius = size.minDimension / 2 * radiusScale,
                style = Stroke(size.minDimension * strokeScale),
                alpha = alpha
            )
        }
    }
}

private const val TAG = "Loader07"
private const val ANIMATION_TIME = 2700