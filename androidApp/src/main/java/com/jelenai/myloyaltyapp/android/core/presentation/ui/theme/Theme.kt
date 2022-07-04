package com.jelenai.myloyaltyapp.android.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ColorPalette = lightColors(
    primary = AccentGreen,
    background = TextWhite,
    onBackground = TextBlack,
    onPrimary = DarkGreen,
    surface = Color.White,
    onSurface = LightGray
)

@Composable
fun MyLoyaltyAppTheme(content: @Composable() () -> Unit) {
    MaterialTheme(
        colors = ColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}