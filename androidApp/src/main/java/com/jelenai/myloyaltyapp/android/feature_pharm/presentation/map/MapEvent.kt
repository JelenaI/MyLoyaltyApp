package com.jelenai.myloyaltyapp.android.feature_pharm.presentation.map

import com.jelenai.myloyaltyapp.android.feature_pharm.domain.model.Branch

sealed class MapEvent {
    data class ShowDetailsDialog(val branch: Branch): MapEvent()
    object DismissDetailsDialog: MapEvent()
}