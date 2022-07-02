package com.jelenai.myloyaltyapp.android.feature_auth.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jelenai.myloyaltyapp.android.core.domain.states.StandardTextFieldState
import com.jelenai.myloyaltyapp.android.core.presentation.UiEvent
import com.jelenai.myloyaltyapp.android.core.util.Resource
import com.jelenai.myloyaltyapp.android.core.util.UiText
import com.jelenai.myloyaltyapp.android.feature_auth.domain.use_case.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    private val _firstNameState = mutableStateOf(StandardTextFieldState())
    val firstNameState: State<StandardTextFieldState> = _firstNameState

    private val _lastNameState = mutableStateOf(StandardTextFieldState())
    val lastNameState: State<StandardTextFieldState> = _lastNameState

    private val _phoneNumberState = mutableStateOf(StandardTextFieldState())
    val phoneNumberState: State<StandardTextFieldState> = _phoneNumberState

    private val _emailState = mutableStateOf(StandardTextFieldState())
    val emailState: State<StandardTextFieldState> = _emailState

    private val _usernameState = mutableStateOf(StandardTextFieldState())
    val usernameState: State<StandardTextFieldState> = _usernameState

    private val _passwordState = mutableStateOf(StandardTextFieldState())
    val passwordState: State<StandardTextFieldState> = _passwordState

    private val _registerState = mutableStateOf(RegisterState())
    val registerState: State<RegisterState> = _registerState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EnteredFirstName -> {
                _firstNameState.value = firstNameState.value.copy(
                    text = event.value
                )
            }
            is RegisterEvent.EnteredLastName -> {
                _lastNameState.value = lastNameState.value.copy(
                    text = event.value
                )
            }
            is RegisterEvent.EnteredPhoneNumber -> {
                _phoneNumberState.value = phoneNumberState.value.copy(
                    text = event.value
                )
            }
            is RegisterEvent.EnteredEmail -> {
                _emailState.value = emailState.value.copy(
                    text = event.value
                )
            }
            is RegisterEvent.EnteredUsername -> {
                _usernameState.value = usernameState.value.copy(
                    text = event.value
                )
            }
            is RegisterEvent.EnteredPassword -> {
                _passwordState.value = passwordState.value.copy(
                    text = event.value
                )
            }
            is RegisterEvent.TogglePasswordVisibility -> {
                _registerState.value = registerState.value.copy(
                    isPasswordVisible = !registerState.value.isPasswordVisible
                )
            }
            is RegisterEvent.Register -> {
                register()
            }
        }
    }

    private fun register() {
        viewModelScope.launch {
            _registerState.value = registerState.value.copy(isLoading = true)
            val registerResult = registerUseCase(
                firstName = firstNameState.value.text,
                lastName = lastNameState.value.text,
                phoneNumber = phoneNumberState.value.text,
                email = emailState.value.text,
                username = usernameState.value.text,
                password = passwordState.value.text
            )
            _registerState.value = registerState.value.copy(isLoading = false)
            if (registerResult.firstNameError != null) {
                _firstNameState.value = firstNameState.value.copy(
                    error = registerResult.firstNameError
                )
            }
            if (registerResult.lastNameError != null) {
                _lastNameState.value = lastNameState.value.copy(
                    error = registerResult.lastNameError
                )
            }
            if (registerResult.phoneNumberError != null) {
                _phoneNumberState.value = phoneNumberState.value.copy(
                    error = registerResult.phoneNumberError
                )
            }
            if (registerResult.emailError != null) {
                _emailState.value = emailState.value.copy(
                    error = registerResult.emailError
                )
            }
            if (registerResult.usernameError != null) {
                _usernameState.value = usernameState.value.copy(
                    error = registerResult.usernameError
                )
            }
            if (registerResult.passwordError != null) {
                _passwordState.value = passwordState.value.copy(
                    error = registerResult.passwordError
                )
            }
            when (registerResult.result) {
                is Resource.Success -> {
                    _eventFlow.emit(UiEvent.OnRegister)
                    _usernameState.value = StandardTextFieldState()
                    _emailState.value = StandardTextFieldState()
                    _passwordState.value = StandardTextFieldState()
                }
                is Resource.Error -> {
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            registerResult.result.uiText ?: UiText.unknownError()
                        )
                    )
                }
            }
        }
    }
}