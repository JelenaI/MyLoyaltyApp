package com.jelenai.myloyaltyapp.android.feature_pharm.domain.model

data class Branch(
    val name: String,
    val address: String,
    val phoneNumber: String,
    val longitude: String,
    val latitude: String,
    val workingHours: List<WorkingHours>
)