package com.jelenai.myloyaltyapp.android.feature_auth.data.repository

import android.content.SharedPreferences
import com.jelenai.myloyaltyapp.android.R
import com.jelenai.myloyaltyapp.android.core.util.Resource
import com.jelenai.myloyaltyapp.android.core.util.SimpleResource
import com.jelenai.myloyaltyapp.android.core.util.UiText
import com.jelenai.myloyaltyapp.android.feature_auth.data.remote.AuthApi
import com.jelenai.myloyaltyapp.android.feature_auth.data.remote.request.CreateAccountRequest
import com.jelenai.myloyaltyapp.android.feature_auth.data.remote.request.LoginRequest
import com.jelenai.myloyaltyapp.android.feature_auth.domain.repository.AuthRepository
import com.jelenai.myloyaltyapp.android.util.Constants
import retrofit2.HttpException
import java.io.IOException

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val sharedPreferences: SharedPreferences
) : AuthRepository {
    override suspend fun register(
        firstName: String,
        lastName: String,
        phoneNumber: String,
        email: String,
        username: String,
        password: String
    ): SimpleResource {
        val request =
            CreateAccountRequest(firstName, lastName, phoneNumber, email, username, password)
        return try {
            val response = api.register(request)
            if (response.successful) {
                Resource.Success(Unit)
            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.error_unknown))
            }
        } catch (e: IOException) {
            Resource.Error(UiText.StringResource(R.string.error_couldnt_reach_server))
        } catch (e: HttpException) {
            Resource.Error(UiText.StringResource(R.string.something_went_wrong))
        }
    }

    override suspend fun login(email: String, password: String): SimpleResource {
        val request = LoginRequest(email, password)
        return try {
            val response = api.login(request)
            if (response.successful) {
                response.data?.let { authResponse ->
                    println("Overriding token with ${authResponse.token}")
                    sharedPreferences.edit()
                        .putString(Constants.KEY_JWT_TOKEN, authResponse.token)
                        .putString(Constants.KEY_USER_ID, authResponse.userId)
                        .apply()
                }
                Resource.Success(Unit)
            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.error_unknown))
            }
        } catch (e: IOException) {
            Resource.Error(UiText.StringResource(R.string.error_couldnt_reach_server))
        } catch (e: HttpException) {
            Resource.Error(UiText.StringResource(R.string.something_went_wrong))
        }
    }

    override suspend fun authenticate(): SimpleResource {
        var resource: SimpleResource
        try {
            api.authenticate()
            resource = Resource.Success(Unit)
        } catch (e: IOException) {
            resource = Resource.Error(UiText.StringResource(R.string.error_couldnt_reach_server))
        } catch (e: HttpException) {
            resource = Resource.Error(UiText.StringResource(R.string.something_went_wrong))
        }
        return resource
    }
}