package com.jelenai.myloyaltyapp.android.di

import android.content.SharedPreferences
import com.jelenai.myloyaltyapp.android.core.data.repository.ProfileRepositoryImpl
import com.jelenai.myloyaltyapp.android.core.domain.repository.ProfileRepository
import com.jelenai.myloyaltyapp.android.core.domain.use_case.GetOwnUserIdUseCase
import com.jelenai.myloyaltyapp.android.feature_profile.data.remote.ProfileApi
import com.jelenai.myloyaltyapp.android.feature_profile.domain.use_case.GetPointsUseCase
import com.jelenai.myloyaltyapp.android.feature_profile.domain.use_case.GetProfileUseCase
import com.jelenai.myloyaltyapp.android.feature_profile.domain.use_case.LogoutUseCase
import com.jelenai.myloyaltyapp.android.feature_profile.domain.use_case.ProfileUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {
    @Provides
    @Singleton
    fun provideProfileApi(client: OkHttpClient): ProfileApi {
        return Retrofit.Builder()
            .baseUrl(ProfileApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ProfileApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(
        profileApi: ProfileApi,
        sharedPreferences: SharedPreferences,
        getOwnUserIdUseCase: GetOwnUserIdUseCase
    ): ProfileRepository {
        return ProfileRepositoryImpl(profileApi, sharedPreferences, getOwnUserIdUseCase)
    }

    @Provides
    @Singleton
    fun provideProfileUseCases(repository: ProfileRepository): ProfileUseCases {
        return ProfileUseCases(
            getProfile = GetProfileUseCase(repository),
            getPoints = GetPointsUseCase(repository),
            logout = LogoutUseCase(repository)
        )
    }
}