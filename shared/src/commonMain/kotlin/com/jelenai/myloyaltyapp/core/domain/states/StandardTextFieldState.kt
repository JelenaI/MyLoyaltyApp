package com.jelenai.myloyaltyapp.core.domain.states

import com.jelenai.myloyaltyapp.core.util.Error

data class StandardTextFieldState(
    val text: String = "",
    val error: Error? = null
)
