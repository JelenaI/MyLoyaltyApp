package com.jelenai.myloyaltyapp.android.presentation.util

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jelenai.myloyaltyapp.android.WindowInfo
import com.jelenai.myloyaltyapp.android.core.presentation.util.Screen
import com.jelenai.myloyaltyapp.android.feature_auth.presentation.home.HomeScreen
import com.jelenai.myloyaltyapp.android.feature_auth.presentation.login.LoginScreen
import com.jelenai.myloyaltyapp.android.feature_pharm.presentation.map.MapScreen
import com.jelenai.myloyaltyapp.android.feature_pharm.presentation.pharmacies.PharmaciesScreen
import com.jelenai.myloyaltyapp.android.feature_profile.presentation.profile.ProfileScreen
import com.jelenai.myloyaltyapp.android.presentation.registration.RegisterScreen
import com.jelenai.myloyaltyapp.android.presentation.splash.SplashScreen

@ExperimentalComposeUiApi
@Composable
fun Navigation(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    windowInfo: WindowInfo
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route,
        modifier = Modifier.fillMaxWidth()
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(
                onPopBackStack = navController::popBackStack,
                onNavigate = navController::navigate,
            )
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(
                onNavigate = navController::navigate,
                onLogin = {
                    navController.popBackStack(
                        route = Screen.LoginScreen.route,
                        inclusive = true
                    )
                    navController.navigate(route = Screen.HomeScreen.route)
                },
                scaffoldState = scaffoldState
            )
        }
        composable(Screen.RegisterScreen.route) {
            RegisterScreen(
                navController = navController,
                onPopBackStack = navController::popBackStack,
                scaffoldState = scaffoldState
            )
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen()
        }
        composable(Screen.PharmaciesScreen.route) {
            PharmaciesScreen(
                scaffoldState = scaffoldState,
                windowInfo = windowInfo
            )
        }
        composable(Screen.MapScreen.route) {
            MapScreen(scaffoldState = scaffoldState)
        }
        composable(
            route = Screen.ProfileScreen.route
        ) {
            ProfileScreen(
                scaffoldState = scaffoldState,
                onLogout = {
                    navController.popBackStack()
                    navController.navigate(route = Screen.LoginScreen.route)
                },
                windowInfo = windowInfo
            )
        }
    }
}