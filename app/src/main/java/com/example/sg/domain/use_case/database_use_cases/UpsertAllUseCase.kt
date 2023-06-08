package com.example.sg.domain.use_case.database_use_cases

import com.example.sg.domain.models.Demon
import com.example.sg.domain.repository.DemonRepository
import javax.inject.Inject

class UpsertAllUseCase @Inject constructor(private val repository: DemonRepository) {
    suspend operator fun invoke(demonsList: List<Demon>) {
        repository.upsertAll(demonsList)
    }
}