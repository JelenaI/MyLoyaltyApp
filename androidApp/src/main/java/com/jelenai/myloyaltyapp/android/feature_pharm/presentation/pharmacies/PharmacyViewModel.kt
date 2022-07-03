package com.jelenai.myloyaltyapp.android.feature_pharm.presentation.pharmacies

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jelenai.myloyaltyapp.android.core.presentation.UiEvent
import com.jelenai.myloyaltyapp.android.core.presentation.util.Screen
import com.jelenai.myloyaltyapp.android.core.util.Event
import com.jelenai.myloyaltyapp.android.core.util.Resource
import com.jelenai.myloyaltyapp.android.core.util.UiText
import com.jelenai.myloyaltyapp.android.feature_pharm.domain.use_case.GetPharmaciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PharmacyViewModel @Inject constructor(
    private val pharmaciesUseCase: GetPharmaciesUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(PharmacyState())
    val state: State<PharmacyState> = _state

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: PharmacyEvent) {
        when (event) {
            is PharmacyEvent.ShowPharmacyOnMap -> {
                UiEvent.Navigate(Screen.MapScreen.route)
            }
            is PharmacyEvent.ShowDetailsDialog -> {
                _state.value = state.value.copy(
                    isDetailsDialogVisible = true
                )
            }
            is PharmacyEvent.DismissDetailsDialog -> {
                _state.value = state.value.copy(
                    isDetailsDialogVisible = false
                )
            }
        }
    }

    fun getPharmacies() {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true
            )
            val result = pharmaciesUseCase()
            when (result) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        pharmacies = result.data,
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false
                    )
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            uiText = result.uiText ?: UiText.unknownError()
                        )
                    )
                }
            }
        }
    }
}