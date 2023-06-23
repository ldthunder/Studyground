package com.example.sg.domain.use_case.network_use_cases

import com.example.sg.domain.models.Todo
import com.example.sg.domain.repository.DemonRepository
import javax.inject.Inject

class FetchTodoUseCase @Inject constructor(private val repository: DemonRepository) {
    suspend operator fun invoke(): List<Todo> {
        return repository.fetchTodoCall()
    }
}