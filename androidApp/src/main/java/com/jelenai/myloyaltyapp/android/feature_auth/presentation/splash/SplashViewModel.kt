package com.jelenai.myloyaltyapp.android.feature_auth.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jelenai.myloyaltyapp.android.core.presentation.UiEvent
import com.jelenai.myloyaltyapp.android.core.presentation.util.Screen
import com.jelenai.myloyaltyapp.android.core.util.Resource
import com.jelenai.myloyaltyapp.android.feature_auth.domain.use_case.AuthenticateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authenticateUseCase: AuthenticateUseCase
) : ViewModel() {
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            when (authenticateUseCase()) {
                is Resource.Success -> {
                    _eventFlow.emit(
                        UiEvent.Navigate(Screen.HomeScreen.route)
                    )
                }
                is Resource.Error -> {
                    _eventFlow.emit(
                        UiEvent.Navigate(Screen.LoginScreen.route)
                    )
                }
            }
        }
    }
}