package io.eyram.loaders.util

import android.webkit.WebStorage.Origin
import androidx.compose.ui.geometry.Offset
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin


/**
 * Convert a given Figma rotation value to match rotation value in Compose.
 * Should be used in when value is between 0 to 180 Deg.
 *
 * Turns out, rotations in Figma are done differently compared to what we have in Android.
 * [Read more here](https://help.figma.com/hc/en-us/articles/360039956914-Adjust-alignment-rotation-and-position#Rotation)
 * */
fun Float.toComposeAngle() = when {
    this < 0F -> this * -1F
    else -> 360F - this
}

/**
 * A function that calculates offset from the origin, using polar coordinates.
 * [origin] The center of the coordinate system.
 * [orbitalRadius] The radius of the circle for which the points are calculated.
 * [degreeAngleFromOrigin] The position of a particle in degrees.
 */
fun calculatePlotPoint(
    origin : Offset,
    orbitalRadius: Float,
    degreeAngleFromOrigin: Float
): Offset {
    val radian = degreeAngleFromOrigin * PI / 180
    val x = orbitalRadius * cos(radian)
    val y = orbitalRadius * sin(radian)
    return Offset(x.toFloat(), y.toFloat()) +  origin
}