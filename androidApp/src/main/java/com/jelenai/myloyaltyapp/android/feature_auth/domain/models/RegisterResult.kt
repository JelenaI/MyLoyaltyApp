package com.jelenai.myloyaltyapp.android.feature_auth.domain.models

import com.jelenai.myloyaltyapp.android.core.util.SimpleResource
import com.jelenai.myloyaltyapp.android.feature_auth.presentation.util.AuthError

data class RegisterResult(
    val firstNameError: AuthError? = null,
    val lastNameError: AuthError? = null,
    val phoneNumberError: AuthError? = null,
    val emailError: AuthError? = null,
    val usernameError: AuthError? = null,
    val passwordError: AuthError? = null,
    val result: SimpleResource? = null
)
