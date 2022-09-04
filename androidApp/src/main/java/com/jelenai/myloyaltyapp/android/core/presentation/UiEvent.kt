package com.jelenai.myloyaltyapp.android.core.presentation

import com.jelenai.myloyaltyapp.core.util.Event
import com.jelenai.myloyaltyapp.android.core.util.UiText

sealed class UiEvent : Event() {
    data class ShowSnackbar(val uiText: UiText) : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    object NavigateUp : UiEvent()
    object OnLogin : UiEvent()
    object OnRegister : UiEvent()
}