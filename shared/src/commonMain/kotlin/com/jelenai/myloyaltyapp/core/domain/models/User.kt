package com.jelenai.myloyaltyapp.core.domain.models

data class User(
    val userId: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String,
    val username: String
)
