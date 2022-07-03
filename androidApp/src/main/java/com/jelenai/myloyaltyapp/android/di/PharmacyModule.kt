package com.jelenai.myloyaltyapp.android.di

import com.jelenai.myloyaltyapp.android.core.data.repository.PharmacyRepositoryImpl
import com.jelenai.myloyaltyapp.android.core.domain.repository.PharmacyRepository
import com.jelenai.myloyaltyapp.android.feature_pharm.data.remote.PharmacyApi
import com.jelenai.myloyaltyapp.android.feature_pharm.domain.use_case.GetBranchesUseCase
import com.jelenai.myloyaltyapp.android.feature_pharm.domain.use_case.GetPharmaciesUseCase
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
object PharmacyModule {
    @Provides
    @Singleton
    fun providePharmacyApi(client: OkHttpClient): PharmacyApi {
        return Retrofit.Builder()
            .baseUrl(PharmacyApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(PharmacyApi::class.java)
    }

    @Provides
    @Singleton
    fun providePharmacyRepository(pharmacyApi: PharmacyApi): PharmacyRepository {
        return PharmacyRepositoryImpl(pharmacyApi)
    }

    @Provides
    @Singleton
    fun providePharmacyUseCase(repository: PharmacyRepository): GetPharmaciesUseCase {
        return GetPharmaciesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideBranchesUseCase(repository: PharmacyRepository): GetBranchesUseCase {
        return GetBranchesUseCase(repository)
    }
}