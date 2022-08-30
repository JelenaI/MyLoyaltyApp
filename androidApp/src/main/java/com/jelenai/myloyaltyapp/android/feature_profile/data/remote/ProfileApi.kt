package com.jelenai.myloyaltyapp.android.feature_profile.data.remote

import com.jelenai.myloyaltyapp.core.data.dto.response.BasicApiResponse
import com.jelenai.myloyaltyapp.android.feature_profile.data.remote.response.ProfileResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProfileApi {
    @GET("/user/profile")
    suspend fun getProfile(
        @Query("_id_") userId: String
    ): BasicApiResponse<ProfileResponse>

    companion object {
        const val BASE_URL = "http://10.0.2.2:8100/"
    }
}