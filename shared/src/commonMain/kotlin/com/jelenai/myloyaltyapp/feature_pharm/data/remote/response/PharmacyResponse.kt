package com.jelenai.myloyaltyapp.feature_pharm.data.remote.response

import com.jelenai.myloyaltyapp.feature_pharm.domain.model.Pharmacy
import com.jelenai.myloyaltyapp.feature_pharm.domain.model.Branch

data class PharmacyResponse(
    val name: String,
    val loyaltyDescription: String,
    val branches: List<Branch>
) {
    fun toPharmacy(): Pharmacy {
        return Pharmacy(
            name,
            loyaltyDescription,
            branches
        )
    }
}