package com.example.sg.data.repository

import com.example.sg.data.database.DemonLocal
import com.example.sg.data.database.DemonDao
import com.example.sg.data.mapper.asExternalModel
import com.example.sg.data.mapper.asLocalModel
import com.example.sg.data.network.model.NetworkTodo
import com.example.sg.data.network.service.DemonNetworkDataSource
import com.example.sg.domain.model.Demon
import com.example.sg.domain.model.Todo
import com.example.sg.domain.repository.DemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DemonRepositoryImpl @Inject constructor(
    private val demonDao: DemonDao,
    private val api: DemonNetworkDataSource
    ) : DemonRepository {

    override val allDemons: Flow<List<Demon>>
        get() = demonDao.getDemons().map { it.map(DemonLocal::asExternalModel) }

    override suspend fun upsert(demonLocal: DemonLocal) {
        demonDao.upsertDemon(demonLocal)
    }

    override suspend fun wipeData() {
         demonDao.wipeData()
    }

    override suspend fun updateByNetwork() {
        val result = api.getDemons().forEach {
            demonDao.upsertDemon(it.asLocalModel())
        }
    }

    override suspend fun fetchTodoCall(): List<Todo> {
        val response = try {
            api.getTodos()
        } catch (e: Exception){
            println("YUER = Exception")
            listOf()
        }
        return response.map(NetworkTodo::asExternalModel)
    }
}