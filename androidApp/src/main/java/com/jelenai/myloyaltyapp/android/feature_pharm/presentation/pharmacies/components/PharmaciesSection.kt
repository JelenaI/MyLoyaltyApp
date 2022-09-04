package com.jelenai.myloyaltyapp.android.feature_pharm.presentation.pharmacies.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jelenai.myloyaltyapp.android.R
import com.jelenai.myloyaltyapp.android.WindowInfo
import com.jelenai.myloyaltyapp.android.core.presentation.ui.theme.LightGreen
import com.jelenai.myloyaltyapp.android.core.presentation.ui.theme.TextBlack
import com.jelenai.myloyaltyapp.feature_pharm.domain.model.Pharmacy

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PharmaciesSection(
    pharmacies: List<Pharmacy>,
    windowInfo: WindowInfo
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxHeight(),
        contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 70.dp),
        cells = GridCells.Fixed(if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) 2 else 4),
        content = {
            items(pharmacies.size) { index ->
                PharmacyCard(pharmacy = pharmacies[index])
            }
        }
    )
}

@Composable
fun PharmacyCard(pharmacy: Pharmacy) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(0.65f)
            .clip(RoundedCornerShape(10.dp))
            .background(LightGreen)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = pharmacy.name,
                    style = MaterialTheme.typography.h2,
                    lineHeight = 20.sp
                )
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = stringResource(id = R.string.show_pharmacy_branches_on_map),
                    tint = TextBlack
                )
            }
            Text(
                text = pharmacy.loyaltyDescription,
                style = MaterialTheme.typography.h3,
                lineHeight = 15.sp,
                maxLines = if (isExpanded) Int.MAX_VALUE else 4,
                overflow = if (isExpanded) TextOverflow.Visible else TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .clickable { isExpanded = !isExpanded }
            )
        }
    }
}