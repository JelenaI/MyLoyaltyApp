package com.jelenai.myloyaltyapp.android.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.jelenai.myloyaltyapp.android.core.presentation.ui.theme.AccentGreen
import com.jelenai.myloyaltyapp.android.core.presentation.ui.theme.DarkGreen
import com.jelenai.myloyaltyapp.android.core.presentation.ui.theme.LightGray
import com.jelenai.myloyaltyapp.android.core.presentation.ui.theme.TextWhite

@Composable
fun RowScope.StandardBottomNavigationItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    contentDescription: String,
    selected: Boolean = false,
    onClick: () -> Unit
) {
    BottomNavigationItem(
        selected = selected,
        onClick = onClick,
        modifier = modifier.background(DarkGreen),
        icon = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription,
                    tint = if (selected) {
                        AccentGreen
                    } else {
                        LightGray
                    }
                )

                Text(
                    text = contentDescription,
                    textAlign = TextAlign.Center,
                    fontSize = 10.sp,
                    color = if (selected) {
                        TextWhite
                    } else {
                        LightGray
                    }
                )
            }
        }
    )
}