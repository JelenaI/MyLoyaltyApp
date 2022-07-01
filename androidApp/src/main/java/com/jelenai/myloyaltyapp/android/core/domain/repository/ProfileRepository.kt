package com.jelenai.myloyaltyapp.android.core.domain.repository

import androidx.compose.ui.input.pointer.PointerInputScope
import com.jelenai.myloyaltyapp.android.core.util.Resource
import com.jelenai.myloyaltyapp.android.feature_profile.domain.model.Points
import com.jelenai.myloyaltyapp.android.feature_profile.domain.model.Profile

interface ProfileRepository {
    suspend fun getProfile(userId: String): Resource<Profile>

    suspend fun getPoints(): Resource<List<Points>>

    fun logout()
}