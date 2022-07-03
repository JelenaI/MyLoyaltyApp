package com.jelenai.myloyaltyapp.android.core.data.repository

import com.jelenai.myloyaltyapp.android.R
import com.jelenai.myloyaltyapp.android.core.domain.repository.PharmacyRepository
import com.jelenai.myloyaltyapp.android.core.util.Resource
import com.jelenai.myloyaltyapp.android.core.util.UiText
import com.jelenai.myloyaltyapp.android.feature_pharm.data.remote.PharmacyApi
import com.jelenai.myloyaltyapp.android.feature_pharm.domain.model.Branch
import com.jelenai.myloyaltyapp.android.feature_pharm.domain.model.Pharmacy
import retrofit2.HttpException
import java.io.IOException

class PharmacyRepositoryImpl(
    private val pharmacyApi: PharmacyApi
) : PharmacyRepository {
    override suspend fun getPharmacies(): Resource<List<Pharmacy>> {
        return try {
            val response = pharmacyApi.getPharmacies()
            Resource.Success(
                data = response.map {
                    it.toPharmacy()
                }
            )
        } catch (e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.something_went_wrong)
            )
        }
    }

    override suspend fun getBranches(): Resource<List<Branch>> {
        return try {
            val response = pharmacyApi.getBranches()
            Resource.Success(
                data = response.map {
                    it.toBranch()
                }
            )
        } catch (e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.something_went_wrong)
            )
        }
    }
}