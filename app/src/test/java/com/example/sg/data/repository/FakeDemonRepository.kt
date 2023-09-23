package com.example.sg.data.repository

import com.example.sg.domain.models.Demon
import com.example.sg.domain.models.Todo
import com.example.sg.domain.repository.DemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDemonRepository: DemonRepository {
    private val demons = mutableListOf<Demon>()
    private val todos = mutableListOf<Todo>()

    override val allDemons: Flow<List<Demon>>
        get() = flow { emit(demons) }

    override suspend fun wipeData() {
        demons.clear()
    }

    override suspend fun upsert(demon: Demon) {
        demons.add(demon)
    }

    override suspend fun upsertAll(demonList: List<Demon>) {
        demons.addAll(demonList)
    }

    override suspend fun fetchTodoCall(): List<Todo> {
        return todos
    }

    override suspend fun fetchDemonsCall(): List<Demon> {
        return demons
    }
}