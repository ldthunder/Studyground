package com.example.sg.data.network.models

data class NetworkTodo(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)