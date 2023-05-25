package com.example.sg.domain.repository

import androidx.lifecycle.LiveData
import com.example.sg.data.database.Demon
import com.example.sg.data.network.DemonNetwork

interface DemonRepository {
    val allDemons: LiveData<List<Demon>>

    suspend fun upsert(demon: Demon)

    suspend fun deleteDemon(demon: Demon)

    suspend fun doNetworkCall(): List<DemonNetwork>
}