package com.jelenai.myloyaltyapp.android.feature_auth.presentation.home

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jelenai.myloyaltyapp.android.R
import com.jelenai.myloyaltyapp.android.core.presentation.ui.theme.SpaceLarge
import com.jelenai.myloyaltyapp.android.core.presentation.ui.theme.SpaceSmall
import com.jelenai.myloyaltyapp.android.core.presentation.ui.theme.TextBlack
import com.jelenai.myloyaltyapp.android.core.presentation.ui.theme.TextWhite

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val qrgEncoder = QRGEncoder(viewModel.userId,null, QRGContents.Type.TEXT, 1000);
    qrgEncoder.colorBlack = TextBlack.hashCode()
    qrgEncoder.colorWhite = TextWhite.hashCode()

    Column(
        modifier = Modifier
            .background(TextWhite)
            .padding(20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.welcome),
            color = TextBlack,
            fontSize = 26.sp
        )
        Spacer(modifier = Modifier.height(SpaceLarge))
        Text(
            text = stringResource(id = R.string.qr_instruction),
            color = TextBlack,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(SpaceSmall))
        Image(
            modifier = Modifier
                .scale(0.7f),
            bitmap = qrgEncoder.bitmap.asImageBitmap(),
            contentDescription = stringResource(id = R.string.qr_description)
        )
    }
}