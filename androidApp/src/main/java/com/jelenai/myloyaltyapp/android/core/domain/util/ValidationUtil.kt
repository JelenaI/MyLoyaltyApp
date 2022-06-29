package com.jelenai.myloyaltyapp.android.core.domain.util

import android.util.Patterns
import com.jelenai.myloyaltyapp.android.feature_auth.presentation.util.AuthError
import com.jelenai.myloyaltyapp.android.util.Constants

object ValidationUtil {
    fun validateFirstName(firstName: String): AuthError? {
        val trimmedFirstName = firstName.trim()

        if (trimmedFirstName.isBlank()) {
            return AuthError.FieldEmpty
        }

        return null
    }

    fun validateLastName(lastName: String): AuthError? {
        val trimmedLastName = lastName.trim()

        if (trimmedLastName.isBlank()) {
            return AuthError.FieldEmpty
        }

        return null
    }

    fun validatePhoneNumber(phoneNumber: String): AuthError? {
        val trimmedPhoneNumber = phoneNumber.trim()

        if (trimmedPhoneNumber.isBlank()) {
            return AuthError.FieldEmpty
        }

        return null
    }

    fun validateEmail(email: String): AuthError? {
        val trimmedEmail = email.trim()

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return AuthError.InvalidEmail
        }

        if (trimmedEmail.isBlank()) {
            return AuthError.FieldEmpty
        }

        return null
    }

    fun validateUsername(username: String): AuthError? {
        val trimmedUsrname = username.trim()

        if (trimmedUsrname.length < Constants.MIN_USERNAME_LENGTH) {
            return AuthError.InputTooShort
        }

        if (trimmedUsrname.isBlank()) {
            return AuthError.FieldEmpty
        }

        return null
    }

    fun validatePassword(password: String): AuthError? {
        val capitalLettersInPassword = password.any { it.isUpperCase() }
        val numberInPassword = password.any { it.isDigit() }

        if (!capitalLettersInPassword || !numberInPassword) {
            return AuthError.InvalidPassword
        }

        if (password.length < Constants.MIN_PASSWORD_LENGTH) {
            return AuthError.InputTooShort
        }

        if (password.isBlank()) {
            return AuthError.FieldEmpty
        }

        return null
    }
}