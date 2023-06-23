package com.example.sg.data.repository

import com.example.sg.data.database.DemonDao
import com.example.sg.data.database.DemonLocal
import com.example.sg.data.mapper.asExternalModel
import com.example.sg.data.mapper.asLocalModel
import com.example.sg.data.network.DemonNetworkApi
import com.example.sg.data.network.models.NetworkDemon
import com.example.sg.data.network.models.NetworkTodo
import com.example.sg.domain.models.Demon
import com.example.sg.domain.models.Todo
import com.example.sg.domain.repository.DemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DemonRepositoryImpl @Inject constructor(
    private val demonDao: DemonDao,
    private val api: DemonNetworkApi
) : DemonRepository {

    /* Database work */
    override val allDemons: Flow<List<Demon>>
        get() = demonDao.getDemons().map {
            it.map(DemonLocal::asExternalModel)
        }

    override suspend fun upsertAll(demonList: List<Demon>) {
        demonDao.upsertAll(demonList.map { it.asLocalModel() })
    }

    override suspend fun upsert(demon: Demon) {
        demonDao.upsertDemon(demon.asLocalModel())
    }

    override suspend fun wipeData() {
        demonDao.wipeData()
    }

    /* Network work */
    override suspend fun fetchDemonsCall(): List<Demon> {
        return try {
            withContext(Dispatchers.IO) {
                api.getDemons().body()!!.map(NetworkDemon::asExternalModel)
            }
        } catch (e: Exception) {
            listOf()
        }
    }

    override suspend fun fetchTodoCall(): List<Todo> {
        return try {
            withContext(Dispatchers.IO) {
                api.getTodos().body()!!.map(NetworkTodo::asExternalModel)
            }
        } catch (e: Exception) {
            listOf()
        }
    }

}