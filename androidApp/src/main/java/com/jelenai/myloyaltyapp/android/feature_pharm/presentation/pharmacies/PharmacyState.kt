package com.jelenai.myloyaltyapp.android.feature_pharm.presentation.pharmacies

import com.jelenai.myloyaltyapp.android.feature_pharm.domain.model.Pharmacy

data class PharmacyState(
    val pharmacies: List<Pharmacy>? = null,
    val isDetailsDialogVisible: Boolean = false
)