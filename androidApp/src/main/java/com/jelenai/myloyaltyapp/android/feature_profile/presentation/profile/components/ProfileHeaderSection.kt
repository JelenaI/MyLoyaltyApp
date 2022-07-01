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
import com.jelenai.myloyaltyapp.android.R
import com.jelenai.myloyaltyapp.android.core.domain.models.User
import com.jelenai.myloyaltyapp.android.presentation.ui.theme.SpaceSmall

@Composable
fun ProfileHeaderSection(
    user: User,
    modifier: Modifier = Modifier,
    onLogoutClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = user.firstName + " " + user.lastName
        )
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
        Spacer(modifier = Modifier.width(SpaceSmall))
        IconButton(
            onClick = onLogoutClick,
            modifier = Modifier.size(30.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Logout,
                contentDescription = stringResource(id = R.string.logout)
            )
        }
    }
}