package com.jelenai.myloyaltyapp.android.feature_pharm.presentation.map

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jelenai.myloyaltyapp.android.core.presentation.UiEvent
import com.jelenai.myloyaltyapp.core.util.Event
import com.jelenai.myloyaltyapp.android.core.util.Resource
import com.jelenai.myloyaltyapp.android.core.util.UiText
import com.jelenai.myloyaltyapp.android.feature_pharm.domain.use_case.GetBranchesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val branchesUseCase: GetBranchesUseCase
): ViewModel() {
    private val _state = mutableStateOf(MapState())
    val state: State<MapState> = _state

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: MapEvent) {
        when (event) {
            is MapEvent.ShowDetailsDialog -> {
                _state.value = state.value.copy(
                    isDetailsDialogVisible = true,
                    branchForDetailsDialog = event.branch
                )
            }
            is MapEvent.DismissDetailsDialog -> {
                _state.value = state.value.copy(
                    isDetailsDialogVisible = false
                )
            }
        }
    }

    fun getBranches() {
        viewModelScope.launch {
            val result = branchesUseCase()
            when (result) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        branches = result.data
                    )
                }
                is Resource.Error -> {
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