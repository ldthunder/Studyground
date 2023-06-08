package com.example.sg.domain.use_case.network_use_cases

import com.example.sg.domain.use_case.UpsertAllFromNetworkUseCase
import javax.inject.Inject

data class NetworkUseCases @Inject constructor(
    val fetchDemonsUseCase: FetchDemonsUseCase,
    val fetchTodoUseCase: FetchTodoUseCase,
    val upsertAllFromNetworkUseCase: UpsertAllFromNetworkUseCase
)