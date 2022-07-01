package com.jelenai.myloyaltyapp.android.feature_auth.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jelenai.myloyaltyapp.android.R
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.SpaceSmall
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.TextBlack
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.TextWhite

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
    val state = viewModel.profileState.value
    Column(
        modifier = Modifier
            .background(TextWhite)
            .padding(20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.welcome),
                color = TextBlack,
                fontSize = 26.sp
            )
            Spacer(modifier = Modifier.height(SpaceSmall))
            Text(
                text = stringResource(id = R.string.qr_instruction),
                color = TextBlack,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(SpaceSmall))
        }
    }
}