package com.example.sg.domain.repository

import com.example.sg.data.database.DemonLocal
import com.example.sg.data.network.models.NetworkDemon
import com.example.sg.data.network.models.NetworkTodo
import com.example.sg.domain.models.Demon
import kotlinx.coroutines.flow.Flow

interface DemonRepository {
    val allDemons: Flow<List<DemonLocal>>

    suspend fun wipeData()

    suspend fun upsert(demon: DemonLocal)

    suspend fun upsertAll(demonList: List<Demon>)

    suspend fun fetchTodoCall(): List<NetworkTodo>

    suspend fun fetchDemonsCall(): List<NetworkDemon>
}