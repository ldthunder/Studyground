package com.example.sg.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.sg.domain.models.Demon
import com.example.sg.domain.use_case.database_use_cases.DatabaseUseCases
import com.example.sg.domain.use_case.UpsertAllFromNetworkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val databaseUseCases: DatabaseUseCases,
    private val upsertAllFromNetworkUseCase: UpsertAllFromNetworkUseCase
) : ViewModel() {

    // All Data
    val demonsList: LiveData<List<Demon>> = databaseUseCases.getAllDemonsUseCase().asLiveData()


    fun wipeData(){
        viewModelScope.launch {
            databaseUseCases.wipeDatabaseUseCase()
        }
    }

    fun upsert(demon: Demon){
        viewModelScope.launch {
            databaseUseCases.upsertToDatabaseUseCase(demon)
        }
    }


    fun upsertAllFromNetwork(){
        viewModelScope.launch {
            upsertAllFromNetworkUseCase()
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}
