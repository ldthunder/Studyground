package com.example.sg.domain.repository

import com.example.sg.data.network.models.NetworkDemon
import com.example.sg.domain.models.Demon
import com.example.sg.domain.models.Todo
import kotlinx.coroutines.flow.Flow

interface DemonRepository {
    val allDemons: Flow<List<Demon>>

    suspend fun upsert(demon: Demon)

    suspend fun upsertAllNetwork(demons: List<NetworkDemon>)

    suspend fun wipeData()

    suspend fun updateByNetwork()

    suspend fun fetchTodoCall(): List<Todo>

}