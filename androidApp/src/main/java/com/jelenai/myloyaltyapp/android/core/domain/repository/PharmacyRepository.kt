package com.jelenai.myloyaltyapp.android.core.domain.repository

import com.jelenai.myloyaltyapp.android.core.util.Resource
import com.jelenai.myloyaltyapp.android.feature_pharm.domain.model.Branch
import com.jelenai.myloyaltyapp.android.feature_pharm.domain.model.Pharmacy

interface PharmacyRepository {
    suspend fun getPharmacies(): Resource<List<Pharmacy>>
    suspend fun getBranches(): Resource<List<Branch>>
}