package com.jelenai.myloyaltyapp.android.feature_profile.data.remote.response

import com.jelenai.myloyaltyapp.android.feature_profile.domain.model.Points

data class PointsDto(
    val userId: String,
    val pharmacyId: String,
    val numberOfPoints: Double
) {
    fun toPoints(): Points {
        return Points(
            userId,
            pharmacyId,
            numberOfPoints
        )
    }
}
