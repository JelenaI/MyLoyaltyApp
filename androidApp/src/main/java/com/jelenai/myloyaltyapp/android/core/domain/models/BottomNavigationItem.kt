package com.jelenai.myloyaltyapp.android.core.domain.models

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val contentDescription: String,
    val route: String,
    val icon: ImageVector
)