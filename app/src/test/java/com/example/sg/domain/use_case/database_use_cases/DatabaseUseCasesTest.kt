package com.example.sg.domain.use_case.database_use_cases

import com.example.sg.data.repository.FakeDemonRepository
import com.example.sg.domain.models.Demon
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DatabaseUseCasesTest {
    private lateinit var fakeRepository: FakeDemonRepository
    private lateinit var getAllDemons: GetAllDemonsUseCase
    private lateinit var upsertAllUseCase: UpsertAllUseCase
    private lateinit var upsertUseCase: UpsertUseCase
    private lateinit var wipeDatabaseUseCase: WipeDatabaseUseCase

    @Before
    fun setUp() {
        fakeRepository = FakeDemonRepository()
        getAllDemons = GetAllDemonsUseCase(fakeRepository)
        upsertAllUseCase = UpsertAllUseCase(fakeRepository)
        upsertUseCase = UpsertUseCase(fakeRepository)
        wipeDatabaseUseCase = WipeDatabaseUseCase(fakeRepository)

        val demonsToInsert = mutableListOf<Demon>()
        (1..12).forEachIndexed { index, c ->
            demonsToInsert.add(
                Demon(
                    id = c,
                    name = c.toString(),
                    thumbnail = c.toString(),
                    url = c.toString()
                )
            )
        }
        demonsToInsert.shuffle()
        runBlocking {
            demonsToInsert.forEach { fakeRepository.upsert(it) }
        }

    }

    @Test
    fun `Getting all the demons UseCase test`() = runBlocking {
        val demons = getAllDemons.invoke().first()
        assertThat(demons).contains(Demon(2, "2", "2", "2"))
    }

    @Test
    fun `Upserting all into the database UseCase test`() = runBlocking {
        val list = listOf(Demon(15,"15", "15", "15"))
        upsertAllUseCase.invoke(list)
        assertThat(fakeRepository.allDemons.first()).contains(Demon(15,"15", "15", "15"))
    }

    @Test
    fun `Upserting a demon UseCase test`() = runBlocking {
        val demon = Demon(16,"15", "15", "15")
        upsertUseCase.invoke(demon)
        assertThat(fakeRepository.allDemons.first()).contains(Demon(16,"15", "15", "15"))

    }

    @Test
    fun `Wipe all the database UseCase test`() = runBlocking {
        wipeDatabaseUseCase.invoke()
        assertThat(fakeRepository.allDemons.first()).isEmpty()
    }
}