package com.example.sg.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.sg.domain.models.Todo
import com.example.sg.domain.use_case.network_use_cases.NetworkUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NetworkViewModel @Inject constructor(
    private val networkUseCases: NetworkUseCases
    ) : ViewModel() {

    suspend fun fetchTodo(): List<Todo> {
        return withContext(Dispatchers.IO){
            try {
                networkUseCases.fetchTodoUseCase()
            } catch(e: Exception){
                listOf()
            }
        }
    }
}