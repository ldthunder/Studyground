package com.example.sg.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface DemonDao {

    @Upsert
    suspend fun upsertDemon(demonLocal: DemonLocal)

    @Query("SELECT * FROM demon ORDER BY id ASC")
    fun getDemons(): Flow<List<DemonLocal>>

    @Query("DELETE FROM demon")
    suspend fun wipeData()

}