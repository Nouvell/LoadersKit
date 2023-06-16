package io.eyram.loaders.util

import android.webkit.WebStorage.Origin
import androidx.compose.ui.geometry.Offset
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * A function that calculates offset from the origin, using polar coordinates.
 * [origin] The center of the coordinate system.
 * [orbitalRadius] The radius of the circle for which the points are calculated.
 * [degreeAngleFromOrigin] The position of a particle in degrees.
 */
internal fun calculatePlotPoint(
    origin: Offset,
    orbitalRadius: Float,
    degreeAngleFromOrigin: Float
): Offset {
    val radian = degreeAngleFromOrigin * PI / 180
    val x = orbitalRadius * cos(radian)
    val y = orbitalRadius * sin(radian)
    return Offset(x.toFloat(), y.toFloat()) + origin
}

internal fun calculatePlotPoints(
    origin: Offset,
    orbitalRadius: Float,
    numberOfSides: Int,
): List<Offset> {
    val plotPointRadianSeparation = 2 * PI / numberOfSides
    val plotRadianFromOrigin = List(numberOfSides) { index ->
        index * plotPointRadianSeparation
    }

    return plotRadianFromOrigin.map { radian ->
        val x = orbitalRadius * cos(radian)
        val y = orbitalRadius * sin(radian)
        Offset(x.toFloat(), y.toFloat()) + origin
    }
}

internal fun lerp(min: Float, max: Float, fraction: Float) = min + (max - min) * fraction