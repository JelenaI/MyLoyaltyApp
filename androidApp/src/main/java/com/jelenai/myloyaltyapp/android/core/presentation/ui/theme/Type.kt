package com.jelenai.myloyaltyapp.android.core.presentation.ui.theme

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

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = lexend,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = TextBlack
    ),
    body2 = TextStyle(
        fontFamily = lexend,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = TextBlack
    ),
    h1 = TextStyle(
        fontFamily = lexend,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        color = TextBlack
    ),
    h2 = TextStyle(
        fontFamily = lexend,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        color = TextBlack
    ),
    h3 = TextStyle(
        fontFamily = lexend,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        color = DarkGray
    ),
    h4 = TextStyle(
        fontFamily = lexend,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = AccentGreen
    ),
    button = TextStyle(
        fontFamily = lexend,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        color = DarkGray
    )
)