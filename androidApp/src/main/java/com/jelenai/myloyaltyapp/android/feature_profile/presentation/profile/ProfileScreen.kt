package com.jelenai.myloyaltyapp.android.feature_profile.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.jelenai.myloyaltyapp.android.R
import com.jelenai.myloyaltyapp.android.core.domain.models.User
import com.jelenai.myloyaltyapp.android.core.presentation.UiEvent
import com.jelenai.myloyaltyapp.android.core.presentation.util.asString
import com.jelenai.myloyaltyapp.android.feature_profile.presentation.profile.components.PointsSection
import com.jelenai.myloyaltyapp.android.feature_profile.presentation.profile.components.ProfileHeaderSection
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.LightGreen
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.SpaceLarge
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.SpaceMedium
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProfileScreen(
    scaffoldState: ScaffoldState,
    onLogout: () -> Unit = {},
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.getProfile()
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
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            ProfileHeaderSection(
                modifier = Modifier.background(LightGreen),
                user = User(
                    userId = state.profile?.userId ?: "",
                    username = state.profile?.username ?: "",
                    firstName = state.profile?.firstName ?: "",
                    lastName = state.profile?.lastName ?: "",
                    phoneNumber = state.profile?.phoneNumber ?: "",
                    email = state.profile?.email ?: ""
                ),
                onLogoutClick = {
                    viewModel.onEvent(ProfileEvent.ShowLogoutDialog)
                }
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(SpaceMedium),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(SpaceMedium))
                Text(
                    text = stringResource(id = R.string.points_description),
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(SpaceLarge))
                if (state.profile?.points?.isNotEmpty() == true) {
                    Spacer(modifier = Modifier.height(SpaceMedium))
                    PointsSection(
                        points = state.profile.points
                    )
                }
            }
        }
        if (state.isLogoutDialogVisible) {
            Dialog(onDismissRequest = {
                viewModel.onEvent(ProfileEvent.DismissLogoutDialog)
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
                                viewModel.onEvent(ProfileEvent.DismissLogoutDialog)
                            }
                        )
                        Spacer(modifier = Modifier.width(SpaceMedium))
                        Text(
                            text = stringResource(id = R.string.yes).uppercase(),
                            color = MaterialTheme.colors.primary,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable {
                                viewModel.onEvent(ProfileEvent.Logout)
                                viewModel.onEvent(ProfileEvent.DismissLogoutDialog)
                                onLogout()
                            }
                        )
                    }
                }
            }
        }
    }
}