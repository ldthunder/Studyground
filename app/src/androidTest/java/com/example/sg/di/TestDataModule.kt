package com.example.sg.di

import android.app.Application
import androidx.room.Room
import androidx.work.WorkManager
import com.example.sg.data.database.DemonDao
import com.example.sg.data.database.DemonRoomDatabase
import com.example.sg.data.network.DemonNetworkApi
import com.example.sg.data.repository.DemonRepositoryImpl
import com.example.sg.domain.repository.DemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestDataModule {
    @Provides
    @Singleton
    fun provideDao(app: Application): DemonRoomDatabase {
        return Room.inMemoryDatabaseBuilder(
            app,
            DemonRoomDatabase::class.java,
        ).build()
    }

    @Provides
    @Singleton
    fun provideDemonRepository(dao: DemonDao, api: DemonNetworkApi): DemonRepository {
        return DemonRepositoryImpl(dao, api)
    }

    @Provides
    @Singleton
    fun provideWorkManager(app: Application): WorkManager {
        return WorkManager.getInstance(app)
    }
}