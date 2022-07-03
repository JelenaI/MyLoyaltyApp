package com.jelenai.myloyaltyapp.android.feature_pharm.data.remote

import com.jelenai.myloyaltyapp.android.feature_pharm.data.remote.response.PharmacyResponse
import retrofit2.http.GET

interface PharmacyApi {
    @GET("/pharmacy")
    suspend fun getPharmacies(): List<PharmacyResponse>

    companion object {
        const val BASE_URL = "http://10.0.2.2:8100/"
    }
}