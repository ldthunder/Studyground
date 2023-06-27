package com.example.sg.presentation.google_auth

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)