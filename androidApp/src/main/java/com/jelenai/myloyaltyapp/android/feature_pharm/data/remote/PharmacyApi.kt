package com.jelenai.myloyaltyapp.android.feature_pharm.data.remote

import com.jelenai.myloyaltyapp.feature_pharm.data.remote.response.PharmacyResponse
import com.jelenai.myloyaltyapp.feature_pharm.data.remote.response.BranchResponse
import retrofit2.http.GET

interface PharmacyApi {
    @GET("/pharmacies")
    suspend fun getPharmacies(): List<PharmacyResponse>

    @GET("/branches")
    suspend fun getBranches(): List<BranchResponse>

    companion object {
        const val BASE_URL = "http://10.0.2.2:8100/"
    }
}