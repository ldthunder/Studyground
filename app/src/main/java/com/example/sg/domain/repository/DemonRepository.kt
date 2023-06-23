package com.example.sg.domain.repository

import com.example.sg.domain.models.Demon
import com.example.sg.domain.models.Todo
import kotlinx.coroutines.flow.Flow

interface DemonRepository {
    val allDemons: Flow<List<Demon>>

    suspend fun wipeData()

    suspend fun upsert(demon: Demon)

    suspend fun upsertAll(demonList: List<Demon>)

    suspend fun fetchTodoCall(): List<Todo>

    suspend fun fetchDemonsCall(): List<Demon>
}