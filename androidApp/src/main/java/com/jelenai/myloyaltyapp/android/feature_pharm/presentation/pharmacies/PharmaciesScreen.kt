package com.jelenai.myloyaltyapp.android.feature_pharm.presentation.pharmacies

import androidx.compose.foundation.layout.*
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jelenai.myloyaltyapp.android.R
import com.jelenai.myloyaltyapp.android.WindowInfo
import com.jelenai.myloyaltyapp.android.core.presentation.UiEvent
import com.jelenai.myloyaltyapp.android.core.presentation.util.asString
import com.jelenai.myloyaltyapp.android.feature_pharm.presentation.pharmacies.components.PharmaciesSection
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.SpaceMedium
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PharmaciesScreen(
    scaffoldState: ScaffoldState,
    viewModel: PharmacyViewModel = hiltViewModel(),
    windowInfo: WindowInfo
) {
    val state = viewModel.state.value
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.getPharmacies()
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.uiText.asString(context)
                    )
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(SpaceMedium))
            Text(
                text = stringResource(id = R.string.pharmacies_description),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            if (state.pharmacies?.isNotEmpty() == true) {
                Spacer(modifier = Modifier.height(SpaceMedium))
                PharmaciesSection(
                    pharmacies = state.pharmacies,
                    windowInfo = windowInfo
                )
            }
        }
    }
}