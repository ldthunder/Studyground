package com.example.sg.di

import com.example.sg.data.database.DemonDao
import com.example.sg.data.database.DemonRoomDatabase
import com.example.sg.data.repository.DemonRepositoryImpl
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
//
//@Module
//class ExampleModule() {
//
//    @Provides
//    fun provideDemonDao(context: Context): DemonDao {
//        return DemonRoomDatabase.getDatabase(context).demonDao()
//    }
//
//    @Provides
//    fun provideDemonRepository(demonDao: DemonDao): DemonRepositoryImpl{
//        return DemonRepositoryImpl(demonDao)
//    }
//
//    @Provides
//    fun provideRoomViewModelFactory(repository: DemonRepositoryImpl): DemonViewModelFactory {
//        return DemonViewModelFactory(repository)
//    }
//}