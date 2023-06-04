package com.example.sg.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.sg.domain.models.Todo
import com.example.sg.domain.repository.DemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NetworkViewModel @Inject constructor(private val repository: DemonRepository) : ViewModel() {

    suspend fun fetchTodo(): List<Todo> {
        return repository.fetchTodoCall()
    }
}