package io.nouvell.loaderskit.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val fontsDotGoogle = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = io.nouvell.loaders.R.array.com_google_android_gms_fonts_certs
)

val IBMPlexMono = GoogleFont("IBM Plex Mono")

val IBMPlexFontFamily = FontFamily(
    Font(
        googleFont = IBMPlexMono,
        fontProvider = fontsDotGoogle
    )
)

val Typography = Typography(

    displaySmall = TextStyle(
        fontFamily = IBMPlexFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 36.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = IBMPlexFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 24.sp
    ),

    labelSmall = TextStyle(
        fontFamily = IBMPlexFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 11.sp
    )
)