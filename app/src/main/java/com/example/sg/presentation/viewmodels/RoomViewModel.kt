package com.example.sg.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sg.data.database.Demon
import com.example.sg.data.network.DemonNetwork
import com.example.sg.domain.repository.DemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(private val repository: DemonRepository) : ViewModel() {

    private val _demon: MutableLiveData<List<Demon>> = repository.allDemons as MutableLiveData<List<Demon>>
    val demonsList: LiveData<List<Demon>>
        get() = _demon

    fun upsert(demon: Demon) = viewModelScope.launch {
        repository.upsert(demon)
    }


    fun deleteDemon(demon: Demon){
        viewModelScope.launch {
            repository.deleteDemon(demon)
        }
    }

    suspend fun logTheNetwork(): List<DemonNetwork> {
        val response = viewModelScope.async {
            repository.doNetworkCall()
        }
        return response.await()
    }


    override fun onCleared() {
        super.onCleared()
    }
}
