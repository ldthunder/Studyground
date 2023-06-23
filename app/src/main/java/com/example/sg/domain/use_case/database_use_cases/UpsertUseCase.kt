package com.example.sg.domain.use_case.database_use_cases

import com.example.sg.domain.models.Demon
import com.example.sg.domain.repository.DemonRepository
import javax.inject.Inject

class UpsertUseCase @Inject constructor(private val repository: DemonRepository) {
    suspend operator fun invoke(demon: Demon){
        repository.upsert(demon)
    }
}