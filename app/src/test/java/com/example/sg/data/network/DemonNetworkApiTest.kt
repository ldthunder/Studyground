package com.example.sg.data.network

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

class DemonNetworkApiTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var demonNetworkApi: DemonNetworkApi

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        demonNetworkApi = retrofit.create(DemonNetworkApi::class.java)
    }

    @Test
    fun `Test getDemons() api call`() = runBlocking {
        // Given
        val mockedResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody("[{\"name\":\"User1\"},{\"name\":\"User2\"}]")
        mockWebServer.enqueue(mockedResponse)

        // When
        val response = demonNetworkApi.getDemons()

        // Then
        assertTrue(response.isSuccessful)
        assertEquals(response.body()?.size, 2)
    }

    @Test
    fun `Test getTodos() api call`() = runBlocking {
        // Given
        val mockedResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody("[{\"name\":\"User1\"},{\"name\":\"User2\"}]")
        mockWebServer.enqueue(mockedResponse)

        // When
        val response = demonNetworkApi.getTodos()

        // Then
        assertTrue(response.isSuccessful)
        assertEquals(response.body()?.size, 2)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}