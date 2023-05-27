package com.example.sg.domain.repository

import com.example.sg.data.database.Demon
import com.example.sg.data.network.DemonNetwork
import kotlinx.coroutines.flow.Flow

interface DemonRepository {
    val allDemons: Flow<List<Demon>>

    suspend fun upsert(demon: Demon)

    suspend fun deleteDemon(demon: Demon)

    suspend fun doNetworkCall(): List<DemonNetwork>
}