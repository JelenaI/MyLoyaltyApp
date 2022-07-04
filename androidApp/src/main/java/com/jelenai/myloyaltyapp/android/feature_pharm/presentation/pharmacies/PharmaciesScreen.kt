package com.jelenai.myloyaltyapp.android.feature_pharm.presentation.pharmacies

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.jelenai.myloyaltyapp.android.R
import com.jelenai.myloyaltyapp.android.core.presentation.UiEvent
import com.jelenai.myloyaltyapp.android.core.presentation.util.asString
import com.jelenai.myloyaltyapp.android.feature_pharm.presentation.pharmacies.components.PharmaciesSection
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.LightGray
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.SpaceHuge
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.SpaceMedium
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PharmaciesScreen(
    scaffoldState: ScaffoldState,
    viewModel: PharmacyViewModel = hiltViewModel()
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
                    pharmacies = state.pharmacies
                )
            }
        }
        if (state.isDetailsDialogVisible) {
            Dialog(onDismissRequest = {
                viewModel.onEvent(PharmacyEvent.DismissDetailsDialog)
            }) {
                Column(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colors.surface,
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(SpaceMedium)
                ) {
                    Text(text = stringResource(id = R.string.do_you_want_to_logout))
                    Spacer(modifier = Modifier.height(SpaceMedium))
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(
                            text = stringResource(id = R.string.no).uppercase(),
                            color = MaterialTheme.colors.onBackground,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable {
                                viewModel.onEvent(PharmacyEvent.DismissDetailsDialog)
                            }
                        )
                        Spacer(modifier = Modifier.width(SpaceMedium))
                        Text(
                            text = stringResource(id = R.string.yes).uppercase(),
                            color = MaterialTheme.colors.primary,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable {
                                viewModel.onEvent(PharmacyEvent.ShowPharmacyOnMap)
                                viewModel.onEvent(PharmacyEvent.DismissDetailsDialog)
                            }
                        )
                    }
                }
            }
        }
    }
}