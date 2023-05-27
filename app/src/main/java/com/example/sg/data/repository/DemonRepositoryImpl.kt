package com.example.sg.data.repository

import com.example.sg.data.database.Demon
import com.example.sg.data.database.DemonDao
import com.example.sg.data.network.DemonApi
import com.example.sg.data.network.DemonNetwork
import com.example.sg.domain.repository.DemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DemonRepositoryImpl @Inject constructor(private val demonDao: DemonDao,
                                              private val api: DemonApi) : DemonRepository {

    override val allDemons: Flow<List<Demon>>
        get() = demonDao.getDemons()

    override suspend fun upsert(demon: Demon) {
        demonDao.upsertDemon(demon)
    }

    override suspend fun deleteDemon(demon: Demon) {
        demonDao.deleteDemon(demon)
    }

    override suspend fun doNetworkCall(): List<DemonNetwork> {
        val response =  try {
            api.getDemons().body()!!
        } catch (e: Exception){
            listOf<DemonNetwork>()
        }
        return response
    }
}