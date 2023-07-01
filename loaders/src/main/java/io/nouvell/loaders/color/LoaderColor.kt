package io.nouvell.loaders.color

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

sealed class LoaderColor {
    data class Single(val color: Color) : LoaderColor()

    data class Multi(val colors: List<Color>) : LoaderColor() {
        init {
            require(colors.isNotEmpty()) { "LoaderColor.Multi - Color list cannot be empty" }
        }
    }

    internal fun getColors(): List<Color> {
        return when (this) {
            is Single -> listOf(color)
            is Multi -> colors
        }
    }

    companion object {

        @Stable
        val Black = Single(Color.Black)

        @Stable
        val White = Single(Color.White)

        @Stable
        val Rainbow = Multi(
            listOf(
                Color(0xFFEB5757),
                Color(0xFFF2994A),
                Color(0xFFF2C94C),
                Color(0xFF219653),
                Color(0xFF27AE60),
                Color(0xFF2F80ED),
                Color(0xFF2D9CDB),
                Color(0xFF9B51E0),
            )
        )

    }
}