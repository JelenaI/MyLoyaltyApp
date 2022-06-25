package com.jelenai.myloyaltyapp.android.presentation

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jelenai.myloyaltyapp.android.BottomNavigationBar
import com.jelenai.myloyaltyapp.android.BottomNavigationItem
import com.jelenai.myloyaltyapp.android.presentation.registration.RegisterScreen
import com.jelenai.myloyaltyapp.android.presentation.util.Navigation
import com.jelenai.myloyaltyapp.android.presentation.util.Screen
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.LightGreen
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.MyLoyaltyAppTheme
import kotlinx.coroutines.flow.first

class MainActivity : AppCompatActivity() {
    val Context.dataStore by preferencesDataStore(name = "settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyLoyaltyAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route

                    Scaffold(
                        backgroundColor = LightGreen,
                        bottomBar = {
                            if (currentRoute != Screen.SplashScreen.route &&
                                    currentRoute != Screen.LoginScreen.route &&
                                    currentRoute != Screen.RegisterScreen.route) {
                                BottomNavigationBar(
                                    items = listOf(
                                        BottomNavigationItem(
                                            name = "Početna",
                                            route = "home_screen",
                                            icon = Icons.Default.QrCode2
                                        ),
                                        BottomNavigationItem(
                                            name = "Apoteke",
                                            route = "pharmacies_screen",
                                            icon = Icons.Default.LocalHospital
                                        ),
                                        BottomNavigationItem(
                                            name = "Mapa",
                                            route = "map_screen",
                                            icon = Icons.Default.Map
                                        ),
                                        BottomNavigationItem(
                                            name = "Profil",
                                            route = "profile_screen",
                                            icon = Icons.Default.Person
                                        )
                                    ),
                                    navController = navController,
                                    onItemClick = { item ->
                                        navController.navigate(item.route)
                                    }
                                )
                            } else Unit
                        }
                    ) {
                        Navigation(navController = navController)
                    }
                }
            }
        }
    }

    suspend fun savePreference(key: String, value: String) {
        val dataStoreKey = stringPreferencesKey(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    suspend fun readPreference(key: String): String? {
        val dataStoreKey = stringPreferencesKey(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }
}