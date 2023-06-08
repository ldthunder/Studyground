package com.example.sg.domain.use_case.network_use_cases

import com.example.sg.data.mapper.asExternalModel
import com.example.sg.data.network.models.NetworkDemon
import com.example.sg.domain.models.Demon
import com.example.sg.domain.repository.DemonRepository
import javax.inject.Inject

class FetchDemonsUseCase @Inject constructor(private val repository: DemonRepository) {
    suspend operator fun invoke(): List<Demon> {
        return repository.fetchDemonsCall().map(NetworkDemon::asExternalModel)
    }
}