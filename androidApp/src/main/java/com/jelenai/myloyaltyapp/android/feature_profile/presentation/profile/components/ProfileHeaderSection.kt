package com.jelenai.myloyaltyapp.android.feature_profile.presentation.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jelenai.myloyaltyapp.android.R
import com.jelenai.myloyaltyapp.android.core.domain.models.User
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.SpaceMedium
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.SpaceSmall
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.TextBlack

@Composable
fun ProfileHeaderSection(
    user: User,
    modifier: Modifier = Modifier,
    onLogoutClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(SpaceMedium),
        horizontalAlignment = Alignment.Start,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = user.firstName + " " + user.lastName,
                fontSize = 26.sp
            )
            IconButton(
                onClick = onLogoutClick,
                modifier = Modifier.size(30.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Logout,
                    contentDescription = stringResource(id = R.string.logout),
                    tint = TextBlack
                )
            }
        }
        Spacer(modifier = Modifier.height(SpaceSmall))
        Text(
            text = user.username
        )
        Spacer(modifier = Modifier.height(SpaceSmall))
        Text(
            text = user.email
        )
        Spacer(modifier = Modifier.height(SpaceSmall))
        Text(
            text = user.phoneNumber
        )
    }
}