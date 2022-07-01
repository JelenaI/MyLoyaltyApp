package com.jelenai.myloyaltyapp.android.feature_pharm.presentation.pharmacies

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.TextBlack

@Composable
fun PharmaciesScreen(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "PHARMACIES SCREEN", color = TextBlack)
    }
}