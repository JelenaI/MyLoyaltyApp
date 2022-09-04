package com.jelenai.myloyaltyapp.android.feature_profile.presentation.profile

import com.jelenai.myloyaltyapp.feature_profile.domain.model.Profile

data class ProfileState(
    val profile: Profile? = null,
    val isLogoutDialogVisible: Boolean = false
)
