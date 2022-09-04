package com.jelenai.myloyaltyapp.android.feature_auth.presentation.util

import com.jelenai.myloyaltyapp.core.util.Error

sealed class AuthError : Error() {
    object FieldEmpty : AuthError()
    object InputTooShort : AuthError()
    object InvalidEmail : AuthError()
    object InvalidPassword : AuthError()
}
