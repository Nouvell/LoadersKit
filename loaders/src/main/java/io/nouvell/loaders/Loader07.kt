package io.nouvell.loaders

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import io.nouvell.loaders.util.ANIMATION_TIME
import io.nouvell.loaders.util.Dimens
import kotlinx.coroutines.delay
import kotlin.math.min

@Composable
fun Loader07(
    modifier: Modifier = Modifier
) {

    val brush = remember { SolidColor(Color.White) }
    val density = LocalDensity.current

    val transition = rememberInfiniteTransition("${TAG} InfiniteTransition")

    val alphaTransition by transition.animateFloat(
        initialValue = 0f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = ANIMATION_TIME
                0f atFraction 0f
                1f atFraction 0.8f with EaseInOut
                0.2f atFraction 1f
            },
        ),
        label = "$TAG AlphaTransition"
    )


    val radiusTransition by transition.animateFloat(
        initialValue = 0f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = ANIMATION_TIME
                0.3f atFraction 0f
                0.8f atFraction 0.8f with EaseInOut
                1f atFraction 1f
            }
        ),
        label = "$TAG AlphaTransition"
    )

    val radiusTransition2 by transition.animateFloat(
        initialValue = 0f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = ANIMATION_TIME
//                delayMillis  = (ANIMATION_TIME * 0.8f).toInt()
                0.3f atFraction 0f
                0.8f atFraction 0.8f with EaseInOut
                1f atFraction 1f
            }
        ),
        label = "$TAG AlphaTransition2"
    )

    val alphaTransition2 by transition.animateFloat(
        initialValue = 0f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = ANIMATION_TIME
//                delayMillis  = (ANIMATION_TIME * 0.8f).toInt()
                0f atFraction 0f
                1f atFraction 0.8f with EaseInOut
                0.2f atFraction 1f
            },
        ),
        label = "$TAG AlphaTransition2"
    )


//    val radiusTransition2 by transition.animateFloat(
//        initialValue = 0f,
//        targetValue = 0f,
//        animationSpec = infiniteRepeatable(
//            animation = keyframes {
//                durationMillis = ANIMATION_TIME
////                delayMillis  = (ANIMATION_TIME * 0.8f).toInt()
//                0.3f atFraction 0f
//                0.8f atFraction 0.8f with EaseInOut
//                1f atFraction 1f
//            }
//        ),
//        label = "$TAG AlphaTransition2"
//    )


    Canvas(
        modifier = modifier.size(80.dp)
    ) {
        drawCircle(
            brush = SolidColor(Color.White.copy(alpha = alphaTransition)),
            radius = (min(size.height, size.width) / 2) * radiusTransition,
            style = Stroke(width = with(density) { 5.dp.toPx() })
        )

        drawCircle(
            brush = SolidColor(Color.White.copy(alpha = alphaTransition2)),
            radius = (min(size.height, size.width) / 2) * radiusTransition2,
            style = Stroke(width = with(density) { 5.dp.toPx() })
        )
    }
}


private const val TAG = "Loader07"