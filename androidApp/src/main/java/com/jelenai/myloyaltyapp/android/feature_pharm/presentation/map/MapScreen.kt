package com.jelenai.myloyaltyapp.android.feature_pharm.presentation.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.jelenai.myloyaltyapp.android.R
import com.jelenai.myloyaltyapp.android.core.presentation.UiEvent
import com.jelenai.myloyaltyapp.android.core.presentation.util.asString
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.SpaceMedium
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.SpaceSmall
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MapScreen(
    scaffoldState: ScaffoldState,
    viewModel: MapViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val context = LocalContext.current
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(44.810131, 20.443196), 12f)
    }

    LaunchedEffect(key1 = true) {
        viewModel.getBranches()
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

    val uiSettings = remember {
        MapUiSettings(
            zoomControlsEnabled = false,
            myLocationButtonEnabled = true
        )
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        properties = state.properties,
        uiSettings = uiSettings,
        cameraPositionState = cameraPositionState
    ) {
        state.branches?.forEach { branch ->
            Marker(
                position = LatLng(branch.latitude.toDouble(), branch.longitude.toDouble()),
                title = branch.name,
                onClick = {
                    it.showInfoWindow()
                    true
                },
                onInfoWindowClick = {
                    viewModel.onEvent(MapEvent.ShowDetailsDialog(branch))
                },
                icon = BitmapDescriptorFactory.defaultMarker(
                    BitmapDescriptorFactory.HUE_GREEN
                )
            )
        }
    }

    if (state.isDetailsDialogVisible && state.branchForDetailsDialog != null) {
        Dialog(onDismissRequest = {
            viewModel.onEvent(MapEvent.DismissDetailsDialog)
        }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colors.surface,
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(SpaceMedium),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = state.branchForDetailsDialog.name,
                    style = MaterialTheme.typography.h2
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.address_hint),
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        text = state.branchForDetailsDialog.address,
                        color = MaterialTheme.colors.onBackground
                    )
                }
                Spacer(modifier = Modifier.height(SpaceSmall))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.phone_number_hint),
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        text = state.branchForDetailsDialog.phoneNumber,
                        color = MaterialTheme.colors.onBackground
                    )
                }
                Spacer(modifier = Modifier.height(SpaceMedium))
                Text(
                    text = stringResource(id = R.string.working_hours_hint),
                    color = MaterialTheme.colors.onBackground
                )
                state.branchForDetailsDialog.workingHours.forEach { workingHours ->
                    Spacer(modifier = Modifier.height(SpaceSmall))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = workingHours.dayOfWeek,
                            color = MaterialTheme.colors.onBackground
                        )
                        Text(
                            text = workingHours.startHours + " - " + workingHours.endHours,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                }
            }
        }
    }
}