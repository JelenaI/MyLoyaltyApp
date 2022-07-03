package com.jelenai.myloyaltyapp.android.feature_pharm.domain.use_case

import com.jelenai.myloyaltyapp.android.core.domain.repository.PharmacyRepository
import com.jelenai.myloyaltyapp.android.core.util.Resource
import com.jelenai.myloyaltyapp.android.feature_pharm.domain.model.Pharmacy

class GetPharmaciesUseCase(
    private val repository: PharmacyRepository
) {
    suspend operator fun invoke(): Resource<List<Pharmacy>> {
        return repository.getPharmacies()
    }
}