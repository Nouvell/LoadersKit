package io.eyram.loaderskit.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import io.eyram.loaderskit.R

// Set of Material typography styles to start with
val fontsDotGoogle = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = io.eyram.loaders.R.array.com_google_android_gms_fonts_certs
)

val AbrilFatFace = GoogleFont("Abril Fatface")
val IBMPlexMono = GoogleFont("IBM Plex Mono")


val AbrilFontFamily = FontFamily(
    Font(googleFont = AbrilFatFace, fontProvider = fontsDotGoogle)
)

val IBMPlexFontFamily = FontFamily(
    Font(googleFont = IBMPlexMono, fontProvider = fontsDotGoogle)
)


val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = AbrilFontFamily,
        fontWeight = FontWeight.Normal,
    ),
    displaySmall = TextStyle(
        fontFamily = IBMPlexFontFamily,
        fontWeight = FontWeight.Light
    ),
    headlineSmall = TextStyle(
        fontFamily = IBMPlexFontFamily,
        fontWeight = FontWeight.Light
    ),

    labelSmall = TextStyle(
        fontFamily = IBMPlexFontFamily,
        fontWeight = FontWeight.Light
    )
)