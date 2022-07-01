package com.jelenai.myloyaltyapp.android.feature_profile.domain.use_case

data class ProfileUseCases(
    val getProfile: GetProfileUseCase,
    val getPoints: GetPointsUseCase,
    val logout: LogoutUseCase
)
