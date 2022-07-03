package com.jelenai.myloyaltyapp.android.feature_pharm.presentation.map

import com.google.maps.android.compose.MapProperties
import com.jelenai.myloyaltyapp.android.feature_pharm.domain.model.Branch

data class MapState(
    val properties: MapProperties = MapProperties(),
    val branches: List<Branch>? = emptyList(),
    val isLoading: Boolean = false,
    val isDetailsDialogVisible: Boolean = false
)