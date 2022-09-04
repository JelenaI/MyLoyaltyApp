package com.jelenai.myloyaltyapp.feature_auth.data.remote.request

data class CreateAccountRequest(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String,
    val username: String,
    val password: String
)