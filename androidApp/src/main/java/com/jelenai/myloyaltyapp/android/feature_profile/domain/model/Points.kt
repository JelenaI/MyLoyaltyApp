package com.jelenai.myloyaltyapp.android.feature_profile.domain.model

data class Points(
    val userId: String,
    val pharmacyId: String,
    val numberOfPoints: Double
)
