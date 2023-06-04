package com.example.sg

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.example.sg.data.database.DemonRoomDatabase
import org.junit.Before

class DemonLocalDaoTest {
    private lateinit var database: DemonRoomDatabase

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            DemonRoomDatabase::class.java
        ).allowMainThreadQueries().build()
    }

}