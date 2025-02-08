package androidx.compose.flight.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.flight.R
import androidx.compose.material3.MaterialTheme

// Set of Material typography styles to start with

val montserratFontFamily = FontFamily(
    Font(R.font.montserrat_thin, FontWeight.W200),
    Font(R.font.montserrat_thin_italic, FontWeight.W200),
    Font(R.font.montserrat_light, FontWeight.W300),
    Font(R.font.montserrat_light_italic, FontWeight.W300),
    Font(R.font.montserrat_regular, FontWeight.W400),
    Font(R.font.montserrat_medium, FontWeight.W500),
    Font(R.font.montserrat_medium_italic, FontWeight.W500),
    Font(R.font.montserrat_semibold, FontWeight.W600),
    Font(R.font.montserrat_semibold_italic, FontWeight.W600),
    Font(R.font.montserrat_bold, FontWeight.W700),
    Font(R.font.montserrat_bold_italic, FontWeight.W700)
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 57.sp
    ),
    displayMedium = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 45.sp
    ),
    displaySmall = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 36.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 28.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 26.sp
    ),
    titleLarge = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 22.sp
    ),
    titleMedium = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp
    ),
    titleSmall = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp
    ),
    labelLarge = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 11.sp
    )
)