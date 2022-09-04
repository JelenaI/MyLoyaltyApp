package com.jelenai.myloyaltyapp.android.feature_auth.domain.use_case

import com.jelenai.myloyaltyapp.android.core.domain.util.ValidationUtil
import com.jelenai.myloyaltyapp.android.feature_auth.domain.models.RegisterResult
import com.jelenai.myloyaltyapp.android.feature_auth.domain.repository.AuthRepository

class RegisterUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        firstName: String,
        lastName: String,
        phoneNumber: String,
        email: String,
        username: String,
        password: String
    ): RegisterResult {
        val firstNameError = ValidationUtil.validateFirstName(firstName)
        val lastNameError = ValidationUtil.validateLastName(lastName)
        val phoneNumberError = ValidationUtil.validatePhoneNumber(phoneNumber)
        val emailError = ValidationUtil.validateEmail(email)
        val usernameError = ValidationUtil.validateUsername(username)
        val passwordError = ValidationUtil.validatePassword(password)

        if (firstNameError == null
            && lastNameError == null
            && phoneNumberError == null
            && emailError == null
            && usernameError == null
            && passwordError == null
        ) {
            val result = repository.register(
                firstName.trim(),
                lastName.trim(),
                phoneNumber.trim(),
                email.trim(),
                username.trim(),
                password.trim()
            )

            return RegisterResult(result = result)
        }

        return RegisterResult(
            firstNameError = firstNameError,
            lastNameError = lastNameError,
            phoneNumberError = phoneNumberError,
            emailError = emailError,
            usernameError = usernameError,
            passwordError = passwordError
        )
    }
}