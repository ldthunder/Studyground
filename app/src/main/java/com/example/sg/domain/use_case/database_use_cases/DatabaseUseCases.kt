package com.example.sg.domain.use_case.database_use_cases

import javax.inject.Inject

data class DatabaseUseCases @Inject constructor(
    val getAllDemonsUseCase: GetAllDemonsUseCase,
    val upsertAllUseCase: UpsertAllUseCase,
    val upsertToDatabaseUseCase: UpsertToDatabaseUseCase,
    val wipeDatabaseUseCase: WipeDatabaseUseCase
)