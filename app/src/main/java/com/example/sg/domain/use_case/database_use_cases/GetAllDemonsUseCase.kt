package com.example.sg.domain.use_case.database_use_cases

import com.example.sg.domain.models.Demon
import com.example.sg.domain.repository.DemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllDemonsUseCase @Inject constructor(private val repository: DemonRepository) {
    operator fun invoke(): Flow<List<Demon>> {
        return repository.allDemons
    }
}