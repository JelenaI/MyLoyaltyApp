package com.jelenai.myloyaltyapp.android.feature_auth.data.remote

import com.jelenai.myloyaltyapp.core.data.dto.response.BasicApiResponse
import com.jelenai.myloyaltyapp.feature_auth.data.remote.request.CreateAccountRequest
import com.jelenai.myloyaltyapp.feature_auth.data.remote.request.LoginRequest
import com.jelenai.myloyaltyapp.feature_auth.data.remote.response.AuthResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    @POST("/user/create")
    suspend fun register(
        @Body request: CreateAccountRequest
    ): BasicApiResponse<Unit>

    @POST("/user/login")
    suspend fun login(
        @Body request: LoginRequest
    ): BasicApiResponse<AuthResponse>

    @GET("/user/authenticate")
    suspend fun authenticate()

    companion object {
        const val BASE_URL = "http://10.0.2.2:8100/"
    }
}