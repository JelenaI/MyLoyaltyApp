package com.jelenai.myloyaltyapp.android.core.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jelenai.myloyaltyapp.android.core.domain.models.BottomNavigationItem

@Composable
fun StandardScaffold(
    navController: NavController,
    modifier: Modifier = Modifier,
    showBottomBar: Boolean = true,
    state: ScaffoldState,
    bottomNavigationItems: List<BottomNavigationItem> = listOf(
        BottomNavigationItem(
            contentDescription = "Početna",
            route = "home_screen",
            icon = Icons.Default.QrCode2
        ),
        BottomNavigationItem(
            contentDescription = "Apoteke",
            route = "pharmacies_screen",
            icon = Icons.Default.LocalHospital
        ),
        BottomNavigationItem(
            contentDescription = "Mapa",
            route = "map_screen",
            icon = Icons.Default.Map
        ),
        BottomNavigationItem(
            contentDescription = "Profil",
            route = "profile_screen",
            icon = Icons.Default.Person
        )
    ),
    content: @Composable () -> Unit
) {
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    backgroundColor = MaterialTheme.colors.onPrimary,
                    elevation = 5.dp
                ) {
                    BottomNavigation {
                        bottomNavigationItems.forEachIndexed { i, item ->
                            StandardBottomNavigationItem(
                                icon = item.icon,
                                contentDescription = item.contentDescription,
                                selected = navController.currentDestination?.route?.startsWith(item.route) == true,
                            ) {
                                if (navController.currentDestination?.route != item.route) {
                                    navController.popBackStack()
                                    navController.navigate(item.route)
                                }
                            }
                        }
                    }
                }
            }
        },
        scaffoldState = state,
        modifier = modifier
    ) {
        content()
    }
}