package com.jelenai.myloyaltyapp.android.core.data.dto.response

data class BasicApiResponse<T>(
    val successful: Boolean,
    val message: String? = null,
    val data: T? = null
)
