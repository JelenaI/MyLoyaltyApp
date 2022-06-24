package com.jetbrains.kmmktor2.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val ColorPalette = lightColors(
    primary = AccentGreen,
    background = White,
    onBackground =  Black,
    onPrimary = DarkGrey,
    surface = LightGreen,
    onSurface = LightGrey
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