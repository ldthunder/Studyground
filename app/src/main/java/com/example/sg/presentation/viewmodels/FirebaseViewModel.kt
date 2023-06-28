package com.example.sg.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sg.data.mapper.asFirestoreModel
import com.example.sg.presentation.google_auth.SignInResult
import com.example.sg.presentation.google_auth.SignInState
import com.example.sg.presentation.google_auth.UserData
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class FirebaseViewModel @Inject constructor() : ViewModel() {
    val firestore = Firebase.firestore
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

    fun addUserToFirestore(user: UserData){
        val userHashmap = user.asFirestoreModel()
        firestore.collection("users").document(user.userId).set(userHashmap)
    }
    suspend fun getFirebaseUserByUid(user: UserData): DocumentSnapshot? {
        return firestore.collection("users").document(user.userId).get().await()
    }

}