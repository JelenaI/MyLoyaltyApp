package com.jelenai.myloyaltyapp.android.feature_auth.presentation.home

import androidx.lifecycle.ViewModel
import com.jelenai.myloyaltyapp.android.core.domain.use_case.GetOwnUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getOwnUserIdUseCase: GetOwnUserIdUseCase
) : ViewModel() {
    val userId = getOwnUserIdUseCase()
}