package com.jelenai.myloyaltyapp.android.feature_pharm.presentation.pharmacies.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jelenai.myloyaltyapp.android.feature_pharm.domain.model.Pharmacy
import com.jelenai.myloyaltyapp.android.feature_pharm.presentation.pharmacies.PharmacyEvent
import com.jelenai.myloyaltyapp.android.feature_pharm.presentation.pharmacies.PharmacyViewModel

@Composable
fun PharmacySection(
    pharmacy: Pharmacy,
    viewModel: PharmacyViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                viewModel.onEvent(PharmacyEvent.ShowDetailsDialog)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(text = pharmacy.name)
    }
}