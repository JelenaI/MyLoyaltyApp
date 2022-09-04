package com.jelenai.myloyaltyapp.feature_pharm.domain.model

data class Pharmacy(
    val name: String,
    val loyaltyDescription: String,
    val branches: List<Branch>
)