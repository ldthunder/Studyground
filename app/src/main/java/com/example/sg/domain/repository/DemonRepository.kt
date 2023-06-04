package com.example.sg.domain.repository

import com.example.sg.data.database.DemonLocal
import com.example.sg.domain.model.Demon
import com.example.sg.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface DemonRepository {
    val allDemons: Flow<List<Demon>>

    suspend fun upsert(demon: Demon)

    suspend fun wipeData()

    suspend fun updateByNetwork()

    suspend fun fetchTodoCall(): List<Todo>
}