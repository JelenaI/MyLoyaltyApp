package com.jelenai.myloyaltyapp.android.presentation.util

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jelenai.myloyaltyapp.android.HomeScreen
import com.jelenai.myloyaltyapp.android.MapScreen
import com.jelenai.myloyaltyapp.android.PharmaciesScreen
import com.jelenai.myloyaltyapp.android.ProfileScreen
import com.jelenai.myloyaltyapp.android.core.presentation.util.Screen
import com.jelenai.myloyaltyapp.android.feature_auth.presentation.login.LoginScreen
import com.jelenai.myloyaltyapp.android.presentation.registration.RegisterScreen
import com.jelenai.myloyaltyapp.android.presentation.splash.SplashScreen

@ExperimentalComposeUiApi
@Composable
fun Navigation(
    navController: NavHostController,
    scaffoldState: ScaffoldState
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
            HomeScreen(navController = navController)
        }
        composable(Screen.PharmaciesScreen.route) {
            PharmaciesScreen(navController = navController)
        }
        composable(Screen.MapScreen.route) {
            MapScreen(navController = navController)
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }
    }
}