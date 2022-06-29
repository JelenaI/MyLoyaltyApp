package com.jelenai.myloyaltyapp.android.feature_auth.domain.use_case

import com.jelenai.myloyaltyapp.android.core.util.SimpleResource
import com.jelenai.myloyaltyapp.android.feature_auth.domain.repository.AuthRepository

class AuthenticateUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): SimpleResource {
        return repository.authenticate()
    }
}