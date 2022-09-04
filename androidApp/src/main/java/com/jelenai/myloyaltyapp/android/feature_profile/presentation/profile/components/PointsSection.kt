package com.jelenai.myloyaltyapp.android.feature_profile.presentation.profile.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jelenai.myloyaltyapp.android.WindowInfo
import com.jelenai.myloyaltyapp.android.core.presentation.ui.theme.LightGreen
import com.jelenai.myloyaltyapp.feature_profile.domain.model.Points

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PointsSection(
    points: List<Points>,
    windowInfo: WindowInfo,
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxHeight(),
        contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 70.dp),
        cells = GridCells.Fixed(if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) 1 else 2),
        content = {
            items(points.size) { index ->
                PointsCard(
                    points = points[index],
                    windowInfo
                )
            }
        }
    )
}

@Composable
fun PointsCard(points: Points, windowInfo: WindowInfo) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(7.5.dp)
            .aspectRatio(if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) 4f else 2f)
            .clip(
                RoundedCornerShape(10.dp)
            )
            .background(LightGreen)
    ) {
        if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 15.dp),
                text = points.pharmacy,
                style = MaterialTheme.typography.h2,
                lineHeight = 20.sp,
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 15.dp),
                text = points.numberOfPoints.toString(),
                style = MaterialTheme.typography.h2,
                lineHeight = 20.sp
            )
        } else {
            Text(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 15.dp),
                text = points.pharmacy,
                style = MaterialTheme.typography.h2,
                lineHeight = 20.sp,
            )
            Text(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 15.dp),
                text = points.numberOfPoints.toString(),
                style = MaterialTheme.typography.h2,
                lineHeight = 20.sp
            )
        }
    }
}