package com.example.sg.data.mapper

import com.example.sg.data.database.DemonLocal
import com.example.sg.data.network.models.NetworkDemon
import com.example.sg.data.network.models.NetworkTodo
import com.example.sg.domain.models.Demon
import com.example.sg.domain.models.Todo

/* Object to Object */
fun NetworkTodo.asExternalModel() = Todo(
    completed = this.completed,
    id = this.id,
    title = this.title,
    userId = this.userId
)

fun NetworkDemon.asLocalModel() = DemonLocal(
    id = this.id,
    name = this.title,
    thumbnail = this.thumbnailUrl,
    url = this.url
)

fun NetworkDemon.asExternalModel() = Demon(
    id = this.id,
    name = this.title,
    thumbnail = this.thumbnailUrl,
    url = this.url
)

fun DemonLocal.asExternalModel() = Demon(
    id = this.id,
    name = this.name,
    thumbnail = this.thumbnail,
    url = this.url
)

fun Demon.asLocalModel() = DemonLocal(
    id = this.id,
    name = this.name,
    thumbnail = this.thumbnail,
    url = this.url
)
