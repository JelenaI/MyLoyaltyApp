package com.jelenai.myloyaltyapp.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jelenai.myloyaltyapp.Greeting
import com.jetbrains.kmmktor2.android.ui.theme.MyLoyaltyAppTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyLoyaltyAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Greet()
                }
            }
        }
    }
}

@Composable
fun Greet() {
    Text(text = Greeting().greeting())
}
