package com.jelenai.myloyaltyapp.android.feature_auth.presentation.register

sealed class RegisterEvent {
    data class EnteredFirstName(val value: String) : RegisterEvent()
    data class EnteredLastName(val value: String) : RegisterEvent()
    data class EnteredPhoneNumber(val value: String) : RegisterEvent()
    data class EnteredEmail(val value: String) : RegisterEvent()
    data class EnteredUsername(val value: String) : RegisterEvent()
    data class EnteredPassword(val value: String) : RegisterEvent()
    object TogglePasswordVisibility : RegisterEvent()
    object Register : RegisterEvent()
}
