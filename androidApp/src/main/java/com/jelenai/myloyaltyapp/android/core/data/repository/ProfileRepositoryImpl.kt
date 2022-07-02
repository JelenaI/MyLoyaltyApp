package com.jelenai.myloyaltyapp.android.core.data.repository

import android.content.SharedPreferences
import com.jelenai.myloyaltyapp.android.R
import com.jelenai.myloyaltyapp.android.core.domain.repository.ProfileRepository
import com.jelenai.myloyaltyapp.android.core.domain.use_case.GetOwnUserIdUseCase
import com.jelenai.myloyaltyapp.android.core.util.Resource
import com.jelenai.myloyaltyapp.android.core.util.UiText
import com.jelenai.myloyaltyapp.android.feature_profile.data.remote.ProfileApi
import com.jelenai.myloyaltyapp.android.feature_profile.domain.model.Points
import com.jelenai.myloyaltyapp.android.feature_profile.domain.model.Profile
import com.jelenai.myloyaltyapp.android.util.Constants
import retrofit2.HttpException
import java.io.IOException

class ProfileRepositoryImpl(
    private val profileApi: ProfileApi,
    private val sharedPreferences: SharedPreferences,
    private val getOwnUserIdUseCase: GetOwnUserIdUseCase
) : ProfileRepository {
    override suspend fun getProfile(): Resource<Profile> {
        return try {
            val response = profileApi.getProfile(getOwnUserIdUseCase())
            if (response.successful) {
                Resource.Success(response.data?.toProfile())
            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.error_unknown))
            }
        } catch(e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch(e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.something_went_wrong)
            )
        }
    }

    override suspend fun getPoints(): Resource<List<Points>> {
        return try {
            val response = profileApi.getPoints()
            Resource.Success(
                data = response.map { it.toPoints() }
            )
        } catch(e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch(e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.something_went_wrong)
            )
        }
    }

    override fun logout() {
        sharedPreferences.edit()
            .putString(Constants.KEY_JWT_TOKEN, "")
            .putString(Constants.KEY_USER_ID, "")
            .apply()
    }
}