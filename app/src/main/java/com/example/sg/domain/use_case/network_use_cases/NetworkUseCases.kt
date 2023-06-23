package com.example.sg.domain.use_case.network_use_cases

import javax.inject.Inject

data class NetworkUseCases @Inject constructor(
    val fetchDemonsUseCase: FetchDemonsUseCase,
    val fetchTodoUseCase: FetchTodoUseCase,
)