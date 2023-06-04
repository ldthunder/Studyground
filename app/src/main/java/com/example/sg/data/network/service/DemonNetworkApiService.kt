package com.example.sg.data.network.service

import com.example.sg.data.network.BASE_URL
import com.example.sg.data.network.DemonNetworkApi
import com.example.sg.data.network.model.NetworkDemon
import com.example.sg.data.network.model.NetworkTodo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class DemonNetworkApiService @Inject constructor() : DemonNetworkDataSource {

    private val networkApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DemonNetworkApi::class.java)

    override suspend fun getDemons(): List<NetworkDemon> = networkApi.getDemons().body()!!

    override suspend fun getTodos(): List<NetworkTodo> = networkApi.getTodos().body()!!
}