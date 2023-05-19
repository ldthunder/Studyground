package com.example.sg.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface DemonDao {

    @Upsert
    suspend fun upsertDemon(demon: Demon)

    @Delete
    suspend fun deleteDemon(demon: Demon)

    @Query("SELECT * FROM demon ORDER BY id ASC")
    fun getDemons(): Flow<List<Demon>>
}