package com.example.sg.data.repository

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.sg.data.database.Demon
import com.example.sg.data.database.DemonDao
import com.example.sg.data.database.DemonRoomDatabase
import com.example.sg.domain.repository.DemonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.coroutineContext

@Singleton
class DemonRepositoryImpl @Inject constructor(private val demonDao: DemonDao) : DemonRepository {

    override val allDemons: LiveData<List<Demon>>
        get() = demonDao.getDemons().asLiveData()

    override suspend fun upsert(demon: Demon) {
        demonDao.upsertDemon(demon)
    }

    override suspend fun deleteDemon(demon: Demon) {
        demonDao.deleteDemon(demon)
    }

    override suspend fun doNetworkCall() {
        TODO("Not yet implemented")
    }

}