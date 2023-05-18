package com.example.sg.domain.repository

import androidx.lifecycle.LiveData
import com.example.sg.data.database.Demon

interface DemonRepository {
    val allDemons: LiveData<List<Demon>>

    suspend fun doNetworkCall()

    suspend fun upsert(demon: Demon)

    suspend fun deleteDemon(demon: Demon)
}