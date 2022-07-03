package com.jelenai.myloyaltyapp.android.feature_pharm.data.remote.response

import com.jelenai.myloyaltyapp.android.feature_pharm.domain.model.Branch
import com.jelenai.myloyaltyapp.android.feature_pharm.domain.model.WorkingHours

data class BranchResponse(
    val name: String,
    val address: String,
    val phoneNumber: String,
    val longitude: String,
    val latitude: String,
    val workingHours: List<WorkingHours>
) {
    fun toBranch(): Branch {
        return Branch(
            name,
            address,
            phoneNumber,
            longitude,
            latitude,
            workingHours
        )
    }
}
