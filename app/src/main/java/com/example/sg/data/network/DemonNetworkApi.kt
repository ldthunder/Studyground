package com.example.sg.data.network

import com.example.sg.data.network.models.NetworkDemon
import com.example.sg.data.network.models.NetworkTodo
import retrofit2.Response
import retrofit2.http.GET

interface DemonNetworkApi {

    @GET("/albums/1/photos#raw")
    suspend fun getDemons(): Response<List<NetworkDemon>>

    @GET("/users/1/todos")
    suspend fun getTodos(): Response<List<NetworkTodo>>

}

const val BASE_URL: String = "https://jsonplaceholder.typicode.com"