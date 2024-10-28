package org.example.currency_tracker.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import currencytracker.composeapp.generated.resources.Res
import currencytracker.composeapp.generated.resources.space_mono_bold
import currencytracker.composeapp.generated.resources.space_mono_bold_italic
import currencytracker.composeapp.generated.resources.space_mono_italic
import currencytracker.composeapp.generated.resources.space_mono_regular
import org.jetbrains.compose.resources.Font


@Composable
private fun AppFontFamily() = FontFamily(
    Font(
        Res.font.space_mono_regular,
        weight = FontWeight.Normal
    ),
    Font(
        Res.font.space_mono_italic,
        weight = FontWeight.Normal,
        style = FontStyle.Italic
    ),
    Font(
        Res.font.space_mono_bold,
        weight = FontWeight.Bold
    ),
    Font(
        Res.font.space_mono_bold_italic,
        weight = FontWeight.Bold,
        style = FontStyle.Italic
    )
)


@Composable
fun AppTypography(): Typography {
    val spaceMonoFont = AppFontFamily()
    return Typography(
        bodySmall = TextStyle(
            fontFamily = spaceMonoFont,
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
        ),
        bodyMedium = TextStyle(
            fontFamily = spaceMonoFont,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = spaceMonoFont,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        labelMedium = TextStyle(
            fontFamily = spaceMonoFont,
            fontWeight = FontWeight.Normal,
        ),
        headlineMedium = TextStyle(
            fontFamily = spaceMonoFont,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    )
}