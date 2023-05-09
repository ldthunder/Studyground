package com.example.sg.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WorkersViewModel : ViewModel() {
    // LiveData
    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    init {
        viewModelScope.launch {
            // Coroutine that will be canceled when the ViewModel is cleared.
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}