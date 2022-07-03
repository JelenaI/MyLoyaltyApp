package com.jelenai.myloyaltyapp.android.feature_pharm.presentation.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.jelenai.myloyaltyapp.android.core.presentation.UiEvent
import com.jelenai.myloyaltyapp.android.core.presentation.util.asString
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MapScreen(
    scaffoldState: ScaffoldState,
    viewModel: MapViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val context = LocalContext.current

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
    ) {
        state.branches?.forEach { branch ->
            Marker(
                position = LatLng(branch.latitude.toDouble(), branch.longitude.toDouble()),
                title = "Pharmacy (${branch.latitude}, ${branch.longitude})",
                onClick = {
                    it.showInfoWindow()
                    true
                },
                icon = BitmapDescriptorFactory.defaultMarker(
                    BitmapDescriptorFactory.HUE_GREEN
                )
            )
        }
    }
}