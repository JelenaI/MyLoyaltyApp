package com.jelenai.myloyaltyapp.android.presentation.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jelenai.myloyaltyapp.android.HomeScreen
import com.jelenai.myloyaltyapp.android.MapScreen
import com.jelenai.myloyaltyapp.android.PharmaciesScreen
import com.jelenai.myloyaltyapp.android.ProfileScreen
import com.jelenai.myloyaltyapp.android.presentation.login.LoginScreen
import com.jelenai.myloyaltyapp.android.presentation.registration.RegisterScreen
import com.jelenai.myloyaltyapp.android.presentation.splash.SplashScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.RegisterScreen.route) {
            RegisterScreen(navController = navController)
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