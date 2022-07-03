package com.jelenai.myloyaltyapp.android.feature_pharm.presentation.pharmacies

import com.jelenai.myloyaltyapp.android.feature_pharm.domain.model.Pharmacy

data class PharmacyState(
    val pharmacies: List<Pharmacy>? = null,
    val isLoading: Boolean = false,
    val isDetailsDialogVisible: Boolean = false
)