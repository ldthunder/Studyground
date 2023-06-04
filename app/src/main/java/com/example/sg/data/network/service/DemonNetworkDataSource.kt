package com.example.sg.data.network.service

import com.example.sg.data.network.models.NetworkDemon
import com.example.sg.data.network.models.NetworkTodo

interface DemonNetworkDataSource {
    suspend fun getDemons(): List<NetworkDemon>

    suspend fun getTodos(): List<NetworkTodo>
}