package com.example.sg.domain.use_case

import com.example.sg.data.mapper.asExternalModel
import com.example.sg.domain.repository.DemonRepository
import javax.inject.Inject

class UpsertAllFromNetworkUseCase @Inject constructor(
    private val repository: DemonRepository
    ) {
    suspend operator fun invoke(){
        val response = repository.fetchDemonsCall()
        repository.upsertAll(response.map { it.asExternalModel() })
    }
}