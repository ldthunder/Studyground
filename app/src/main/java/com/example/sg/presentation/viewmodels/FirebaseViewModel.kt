package com.example.sg.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sg.presentation.google_auth.SignInResult
import com.example.sg.presentation.google_auth.SignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirebaseViewModel @Inject constructor() : ViewModel() {
    // LiveData
    private val _state = MutableLiveData<SignInState>()
    val state: LiveData<SignInState>
        get() = _state

    fun onSignInResult(result: SignInResult) {
        _state.postValue(
            SignInState(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        )
    }
}