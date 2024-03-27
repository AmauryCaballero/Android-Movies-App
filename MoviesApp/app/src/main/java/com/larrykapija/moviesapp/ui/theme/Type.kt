package com.larrykapija.moviesapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.larrykapija.moviesapp.R

// Set of Material typography styles to start with

val JostFontFamily = FontFamily(
    Font(R.font.jost_bold, FontWeight.Bold),
    Font(R.font.jost_medium, FontWeight.Medium),
    Font(R.font.jost_light, FontWeight.Light)
)


val Typography = Typography(

    titleLarge = TextStyle(
        fontFamily = JostFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 34.sp,
        letterSpacing = 0.5.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = JostFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = JostFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
    ),
)