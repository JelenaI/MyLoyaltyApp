package com.jelenai.myloyaltyapp.android.core.domain.states

import com.jelenai.myloyaltyapp.android.core.util.Error

data class StandardTextFieldState(
    val text: String = "",
    val error: Error? = null
)
