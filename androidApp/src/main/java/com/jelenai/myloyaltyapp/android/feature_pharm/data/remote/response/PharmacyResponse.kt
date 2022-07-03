package com.jelenai.myloyaltyapp.android.feature_pharm.data.remote.response

import com.jelenai.myloyaltyapp.android.feature_pharm.domain.model.Branch
import com.jelenai.myloyaltyapp.android.feature_pharm.domain.model.Pharmacy

data class PharmacyResponse(
    val name: String,
    val branches: List<Branch>
) {
    fun toPharmacy(): Pharmacy {
        return Pharmacy(
            name,
            branches
        )
    }
}