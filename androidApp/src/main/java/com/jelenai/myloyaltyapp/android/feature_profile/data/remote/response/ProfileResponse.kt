package com.jelenai.myloyaltyapp.android.feature_profile.data.remote.response

import com.jelenai.myloyaltyapp.android.feature_profile.domain.model.Profile

data class ProfileResponse(
    val userId: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String,
    val username: String
) {
    fun toProfile(): Profile {
        return Profile(
            userId,
            firstName,
            lastName,
            phoneNumber,
            email,
            username
        )
    }
}
