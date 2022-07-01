package com.jelenai.myloyaltyapp.android.feature_profile.domain.use_case

import com.jelenai.myloyaltyapp.android.core.domain.repository.ProfileRepository

class LogoutUseCase(
    private val repository: ProfileRepository
) {
    operator fun invoke() {
        repository.logout()
    }
}