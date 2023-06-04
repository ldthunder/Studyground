package com.example.sg.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.sg.domain.model.Demon
import com.example.sg.domain.repository.DemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(private val repository: DemonRepository) : ViewModel() {

    // All Data
    val demonsList: LiveData<List<Demon>> = repository.allDemons.asLiveData()

    fun wipeData(){
        viewModelScope.launch {
            repository.wipeData()
        }
    }
    init {
        viewModelScope.launch {
            repository.updateByNetwork()
        }
    }

    fun fetchDemons(){
        viewModelScope.launch {

        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}
