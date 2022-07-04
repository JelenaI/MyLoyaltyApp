package com.jelenai.myloyaltyapp.android.presentation.registration

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jelenai.myloyaltyapp.android.R
import com.jelenai.myloyaltyapp.android.core.presentation.UiEvent
import com.jelenai.myloyaltyapp.android.core.presentation.util.Screen
import com.jelenai.myloyaltyapp.android.core.presentation.util.asString
import com.jelenai.myloyaltyapp.android.feature_auth.presentation.register.RegisterEvent
import com.jelenai.myloyaltyapp.android.feature_auth.presentation.register.RegisterViewModel
import com.jelenai.myloyaltyapp.android.feature_auth.presentation.util.AuthError
import com.jelenai.myloyaltyapp.android.presentation.components.StandardTextField
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.AccentGreen
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.SpaceHuge
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.SpaceLarge
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.SpaceMedium
import com.jelenai.myloyaltyapp.android.util.Constants
import kotlinx.coroutines.flow.collectLatest

@ExperimentalComposeUiApi
@Composable
fun RegisterScreen(
    navController: NavController,
    scaffoldState: ScaffoldState,
    onPopBackStack: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val firstNameState = viewModel.firstNameState.value
    val lastNameState = viewModel.lastNameState.value
    val phoneNumberState = viewModel.phoneNumberState.value
    val emailState = viewModel.emailState.value
    val usernameState = viewModel.usernameState.value
    val passwordState = viewModel.passwordState.value
    val registerState = viewModel.registerState.value
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        event.uiText.asString(context),
                        duration = SnackbarDuration.Long
                    )
                }
                is UiEvent.Navigate -> {
                    navController.navigate(event.route)
                }
                is UiEvent.OnRegister -> {
                    navController.popBackStack(
                        route = Screen.RegisterScreen.route,
                        inclusive = true
                    )
                    navController.navigate(route = Screen.LoginScreen.route)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = SpaceLarge,
                end = SpaceLarge,
                top = SpaceLarge,
                bottom = SpaceHuge
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.register),
                style = MaterialTheme.typography.h1
            )
            Spacer(modifier = Modifier.height(SpaceLarge))
            StandardTextField(
                text = firstNameState.text,
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.EnteredFirstName(it))
                },
                error = when (firstNameState.error) {
                    is AuthError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    else -> ""
                },
                hint = stringResource(id = R.string.first_name_hint)
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = lastNameState.text,
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.EnteredLastName(it))
                },
                error = when (lastNameState.error) {
                    is AuthError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    else -> ""
                },
                hint = stringResource(id = R.string.last_name_hint)
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = phoneNumberState.text,
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.EnteredPhoneNumber(it))
                },
                error = when (phoneNumberState.error) {
                    is AuthError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    else -> ""
                },
                hint = stringResource(id = R.string.phone_number_hint)
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = emailState.text,
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.EnteredEmail(it))
                },
                error = when (emailState.error) {
                    is AuthError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    is AuthError.InvalidEmail -> stringResource(id = R.string.not_a_valid_email)
                    else -> ""
                },
                keyboardType = KeyboardType.Email,
                hint = stringResource(id = R.string.email_hint)
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = usernameState.text,
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.EnteredUsername(it))
                },
                error = when (usernameState.error) {
                    is AuthError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    is AuthError.InputTooShort -> stringResource(
                        id = R.string.input_too_short,
                        Constants.MIN_USERNAME_LENGTH
                    )
                    else -> ""
                },
                hint = stringResource(id = R.string.username_hint)
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = passwordState.text,
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.EnteredPassword(it))
                },
                error = when (passwordState.error) {
                    is AuthError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    is AuthError.InputTooShort -> stringResource(
                        id = R.string.input_too_short,
                        Constants.MIN_PASSWORD_LENGTH
                    )
                    is AuthError.InvalidPassword -> stringResource(id = R.string.invalid_password)
                    else -> ""
                },
                isPasswordVisible = registerState.isPasswordVisible,
                onPasswordToggleClick = {
                    viewModel.onEvent(RegisterEvent.TogglePasswordVisibility)
                },
                keyboardType = KeyboardType.Password,
                hint = stringResource(id = R.string.password_hint)
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        viewModel.onEvent(RegisterEvent.Register)
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.register_button_text)
                    )
                }
            }
        }
        Row(modifier = Modifier.align(Alignment.BottomCenter)) {
            Text(
                text = buildAnnotatedString {
                    append(stringResource(id = R.string.already_have_an_account_yet))
                    append(" ")
                },
                style = MaterialTheme.typography.body1
            )
            Text(
                text = stringResource(id = R.string.sign_in),
                style = MaterialTheme.typography.h4,
                color = AccentGreen,
                modifier = Modifier
                    .clickable {
                        navController.navigate(Screen.LoginScreen.route)
                    }
            )
        }
    }
}