package com.jelenai.myloyaltyapp.android.core.domain.use_case

import android.content.SharedPreferences
import com.jelenai.myloyaltyapp.android.util.Constants

class GetOwnUserIdUseCase(
    private val sharedPreferences: SharedPreferences
) {
    operator fun invoke(): String {
        println("sharedPreferences.getString(Constants.KEY_USER_ID" + (sharedPreferences.getString(Constants.KEY_USER_ID, "") ?: ""))
        return (sharedPreferences.getString(Constants.KEY_USER_ID, "") ?: "")
    }
}