package com.jelenai.myloyaltyapp.android.feature_profile.domain.model

data class Profile(
    val userId: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String,
    val username: String,
    val points: List<Points>
)