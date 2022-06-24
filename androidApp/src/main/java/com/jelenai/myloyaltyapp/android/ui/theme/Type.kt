package com.jetbrains.kmmktor2.android.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jelenai.myloyaltyapp.android.R

val lexend = FontFamily(
    Font(R.font.lexend_light, FontWeight.Light),
    Font(R.font.lexend_regular, FontWeight.Normal),
    Font(R.font.lexend_medium, FontWeight.Medium),
    Font(R.font.lexend_semibold, FontWeight.SemiBold),
    Font(R.font.lexend_bold, FontWeight.Bold),
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = lexend,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Black
    ),
    h1 = TextStyle(
        fontFamily = lexend,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        color = Black
    ),
    h2 = TextStyle(
        fontFamily = lexend,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        color = Black
    ),
    body2 = TextStyle(
        fontFamily = lexend,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = Black
    )
)