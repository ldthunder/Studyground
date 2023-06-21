package com.example.sg.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.sg.domain.models.Demon
import com.example.sg.domain.use_case.database_use_cases.DatabaseUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(databaseUseCases: DatabaseUseCases) : ViewModel() {

    // All Data
    val demonsList: LiveData<List<Demon>> = databaseUseCases.getAllDemonsUseCase().asLiveData()

}