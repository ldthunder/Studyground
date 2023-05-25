package com.example.sg.data.network

import retrofit2.Response
import retrofit2.http.GET

interface DemonApi {

    @GET("/albums/1/photos#raw")
    suspend fun getDemons(): Response<List<DemonNetwork>>

}