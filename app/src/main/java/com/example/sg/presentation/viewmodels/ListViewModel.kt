package com.example.sg.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.sg.domain.models.Demon
import com.example.sg.domain.repository.DemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(repository: DemonRepository) : ViewModel() {

    // All Data
    val demonsList: LiveData<List<Demon>> = repository.allDemons.asLiveData()

    override fun onCleared() {
        super.onCleared()
    }
}