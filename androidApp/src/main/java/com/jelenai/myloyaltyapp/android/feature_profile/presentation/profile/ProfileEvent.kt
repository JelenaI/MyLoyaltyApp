package com.jelenai.myloyaltyapp.android.feature_profile.presentation.profile

sealed class ProfileEvent {
    object Logout: ProfileEvent()
    object ShowLogoutDialog: ProfileEvent()
    object DismissLogoutDialog: ProfileEvent()
}
