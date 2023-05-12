package io.eyram.loaders


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
