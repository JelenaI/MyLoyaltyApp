package com.jelenai.myloyaltyapp.android.core.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jelenai.myloyaltyapp.android.core.presentation.components.StandardScaffold
import com.jelenai.myloyaltyapp.android.core.presentation.util.Screen
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.MyLoyaltyAppTheme
import com.jelenai.myloyaltyapp.android.presentation.util.Navigation
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyLoyaltyAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val scaffoldState = rememberScaffoldState()

                    StandardScaffold(
                        navController = navController,
                        modifier = Modifier.fillMaxSize(),
                        showBottomBar = shouldShowBottomBar(navBackStackEntry),
                        state = scaffoldState
                    ) {
                        Navigation(
                            navController = navController,
                            scaffoldState = scaffoldState
                        )
                    }
                }
            }
        }
    }

    private fun shouldShowBottomBar(backStackEntry: NavBackStackEntry?): Boolean {
        return backStackEntry?.destination?.route in listOf(
            Screen.HomeScreen.route,
            Screen.PharmaciesScreen.route,
            Screen.MapScreen.route,
            Screen.ProfileScreen.route
        )
    }
}