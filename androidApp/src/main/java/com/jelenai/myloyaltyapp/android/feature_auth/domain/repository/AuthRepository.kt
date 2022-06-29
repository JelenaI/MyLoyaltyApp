package com.jelenai.myloyaltyapp.android.feature_auth.domain.repository

import com.jelenai.myloyaltyapp.android.core.util.SimpleResource

interface AuthRepository {
    suspend fun register(
        firstName: String,
        lastName: String,
        phoneNumber: String,
        email: String,
        username: String,
        password: String
    ): SimpleResource

    suspend fun login(
        email: String,
        password: String
    ): SimpleResource

    suspend fun authenticate(): SimpleResource
}