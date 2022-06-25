package com.jelenai.myloyaltyapp.android.presentation.registration

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jelenai.myloyaltyapp.android.R
import com.jelenai.myloyaltyapp.android.presentation.components.StandardTextField
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.AccentGreen
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.SpaceHuge
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.SpaceLarge
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.SpaceMedium
import com.jelenai.myloyaltyapp.android.presentation.util.Screen

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
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
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(id = R.string.register),
                style = MaterialTheme.typography.h1
            )
            Spacer(modifier = Modifier.height(SpaceLarge))
            StandardTextField(
                text = viewModel.emailText.value,
                onValueChange = {
                    viewModel.setEmailText(it)
                },
                hint = stringResource(id = R.string.email_hint)
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = viewModel.usernameText.value,
                onValueChange = {
                    viewModel.setUsernameText(it)
                },
                hint = stringResource(id = R.string.username_hint)
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                text = viewModel.passwordText.value,
                onValueChange = {
                    viewModel.setPasswordText(it)
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
                    shape = RoundedCornerShape(8.dp),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 6.dp,
                        pressedElevation = 8.dp,
                        disabledElevation = 0.dp
                    ),
                    onClick = {}
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