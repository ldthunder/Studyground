package com.example.sg.domain.use_case.database_use_cases

import com.example.sg.data.database.DemonLocal
import com.example.sg.data.mapper.asExternalModel
import com.example.sg.domain.models.Demon
import com.example.sg.domain.repository.DemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllDemonsUseCase @Inject constructor(private val repository: DemonRepository) {
    operator fun invoke(): Flow<List<Demon>> {
        return repository.allDemons.map { it.map(DemonLocal::asExternalModel) }
    }
}