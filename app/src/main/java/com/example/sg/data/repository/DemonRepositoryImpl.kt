package com.example.sg.data.repository

import com.example.sg.data.database.DemonDao
import com.example.sg.data.database.DemonLocal
import com.example.sg.data.mapper.asLocalModel
import com.example.sg.data.network.models.NetworkDemon
import com.example.sg.data.network.models.NetworkTodo
import com.example.sg.data.network.service.DemonNetworkDataSource
import com.example.sg.domain.models.Demon
import com.example.sg.domain.repository.DemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DemonRepositoryImpl @Inject constructor(
    private val demonDao: DemonDao,
    private val api: DemonNetworkDataSource
    ) : DemonRepository {

    /* Database work */
    override val allDemons: Flow<List<DemonLocal>>
        get() = demonDao.getDemons()

    override suspend fun upsert(demon: DemonLocal) {
        demonDao.upsertDemon(demon)
    }

    override suspend fun wipeData() {
         demonDao.wipeData()
    }

    override suspend fun upsertAll(demonList: List<Demon>){
        demonDao.upsertAll(demonList.map { it.asLocalModel()})
    }

    /* Network work */
    override suspend fun fetchTodoCall(): List<NetworkTodo> {
        return try {
            api.getTodos()
        } catch (e: Exception){
            println("YUER = ${e.stackTraceToString()}")
            listOf()
        }
    }

    override suspend fun fetchDemonsCall(): List<NetworkDemon> {
        return try {
            api.getDemons()
        } catch (e: Exception){
            println("YUER = ${e.stackTraceToString()}")
            listOf()
        }
    }

}