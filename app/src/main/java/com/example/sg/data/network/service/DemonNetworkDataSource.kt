package com.example.sg.data.network.service

import com.example.sg.data.network.model.NetworkDemon
import com.example.sg.data.network.model.NetworkTodo

interface DemonNetworkDataSource {
    suspend fun getDemons(): List<NetworkDemon>

    suspend fun getTodos(): List<NetworkTodo>
}