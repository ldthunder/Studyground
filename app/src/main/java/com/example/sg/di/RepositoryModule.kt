package com.example.sg.di

import com.example.sg.data.repository.DemonRepositoryImpl
import com.example.sg.domain.repository.DemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//abstract class RepositoryModule {
//
//    @Binds
//    @Singleton
//    abstract fun bindDemonRepository(
//        demonRepositoryImpl: DemonRepositoryImpl
//    ): DemonRepository
//}