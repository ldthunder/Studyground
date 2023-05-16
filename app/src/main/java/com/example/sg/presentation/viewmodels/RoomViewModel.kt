package com.example.sg.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.sg.data.database.Demon
import com.example.sg.data.repository.DemonRepositoryImpl
import dagger.internal.InjectedFieldSignature
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RoomViewModel(private val repository: DemonRepositoryImpl) : ViewModel() {

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

    override fun onCleared() {
        super.onCleared()
    }
}
