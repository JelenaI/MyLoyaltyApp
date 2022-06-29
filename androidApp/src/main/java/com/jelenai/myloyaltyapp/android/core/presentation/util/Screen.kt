package com.jelenai.myloyaltyapp.android.core.presentation.util

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("login_screen")
    object RegisterScreen : Screen("register_screen")
    object HomeScreen : Screen("home_screen")
    object PharmaciesScreen : Screen("pharmacies_screen")
    object MapScreen : Screen("map_screen")
    object ProfileScreen : Screen("profile_screen")

    // Funkcija za prosledjivanje argumenata sa jednog screen-a na drugi prilikom navigacije
    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)

            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
