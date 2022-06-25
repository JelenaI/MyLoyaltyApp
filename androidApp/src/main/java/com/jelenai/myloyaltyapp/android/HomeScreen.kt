package com.jelenai.myloyaltyapp.android

import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.TextBlack
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.TextWhite

@Composable
fun HomeScreen(navController: NavHostController) {// Initializing the QR Encoder with your value to be encoded, type you required and Dimension
    val qrgEncoder = QRGEncoder("www.matf.bg.ac.rs", null, QRGContents.Type.TEXT, 1000);

    Column(
        modifier = Modifier
            .background(TextWhite)
            .padding(20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Dobro došli!", 
            color = TextBlack,
            fontSize = 26.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Prilikom kupovine kod partnera aplikacije, pokažite svoj QR kod",
            color = TextBlack,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(0.dp))
        Image(
            modifier = Modifier
                .scale(0.7f),
            bitmap = qrgEncoder.bitmap.asImageBitmap(),
            contentDescription = "QR code"
        )
    }
}