package com.jelenai.myloyaltyapp.android.feature_profile.domain.use_case

import com.jelenai.myloyaltyapp.android.core.domain.repository.ProfileRepository
import com.jelenai.myloyaltyapp.android.core.util.Resource
import com.jelenai.myloyaltyapp.feature_profile.domain.model.Profile

class GetProfileUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(): Resource<Profile> {
        return repository.getProfile()
    }
}