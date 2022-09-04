package com.jelenai.myloyaltyapp.android.core.domain.repository

import com.jelenai.myloyaltyapp.android.core.util.Resource
import com.jelenai.myloyaltyapp.feature_profile.domain.model.Profile

interface ProfileRepository {
    suspend fun getProfile(): Resource<Profile>

    fun logout()
}