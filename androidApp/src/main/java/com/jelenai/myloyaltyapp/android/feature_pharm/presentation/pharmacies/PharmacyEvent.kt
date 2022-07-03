package com.jelenai.myloyaltyapp.android.feature_pharm.presentation.pharmacies

sealed class PharmacyEvent {
    object ShowPharmacyOnMap: PharmacyEvent()
    object ShowDetailsDialog: PharmacyEvent()
    object DismissDetailsDialog: PharmacyEvent()
}