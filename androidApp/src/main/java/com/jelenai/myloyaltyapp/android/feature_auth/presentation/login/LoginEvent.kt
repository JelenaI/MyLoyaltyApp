package com.jelenai.myloyaltyapp.android.feature_auth.presentation.login

sealed class LoginEvent {
    data class EnteredEmail(val email: String) : LoginEvent()
    data class EnteredPassword(val password: String) : LoginEvent()
    object TogglePasswordVisibility : LoginEvent()
    object Login : LoginEvent()
}