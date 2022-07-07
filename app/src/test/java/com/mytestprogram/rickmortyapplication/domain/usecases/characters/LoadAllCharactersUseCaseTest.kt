package com.mytestprogram.rickmortyapplication.domain.usecases.characters

import com.mytestprogram.rickmortyapplication.data.local.entities.characters.LocationEntity
import com.mytestprogram.rickmortyapplication.data.local.entities.characters.OriginEntity
import com.mytestprogram.rickmortyapplication.data.remote.CharacterRetrofitService
import com.mytestprogram.rickmortyapplication.data.repository.CharactersRepositoryImpl
import com.mytestprogram.rickmortyapplication.domain.models.characters.SingleCharacter
import com.mytestprogram.rickmortyapplication.domain.repository.CharactersRepository
import com.mytestprogram.rickmortyapplication.domain.repository.FakeRepository
import com.mytestprogram.rickmortyapplication.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import kotlin.random.Random

class LoadAllCharactersUseCaseTest {
    val characters = listOf(
        SingleCharacter(
            created = "test",
            episode = listOf("test", "test"),
            gender = "test",
            id = 1,
            image = "test",
            location = LocationEntity(name = "test", url = "test"),
            name = "test",
            origin = OriginEntity(name = "test", url = "test"),
            species = "test",
            status = "test",
            type = "test",
            url = "test"
        ),
        SingleCharacter(
            created = "test",
            episode = listOf("test", "test"),
            gender = "test",
            id = 2,
            image = "test",
            location = LocationEntity(name = "test", url = "test"),
            name = "test",
            origin = OriginEntity(name = "test", url = "test"),
            species = "test",
            status = "test",
            type = "test",
            url = "test"
        )

    )


    @Test
    fun `load all characters from repository`() = runBlocking {


        val page = 1

        val fakeRepository = FakeRepository()
        val useCase = LoadAllCharactersUseCase(charactersRepository = fakeRepository)
        val actual = useCase.loadAllCharacters(page).toList()
        val expected = characters

        Assertions.assertEquals(expected, actual)
    }
}