package com.mytestprogram.rickmortyapplication.presentation.location_details

import com.mytestprogram.rickmortyapplication.domain.usecases.characters.LoadMultipleCharactersUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.locations.LoadSingleLocationByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito
import org.mockito.kotlin.mock

class LocationDetailsViewModelTest {


    val loadSingleLocationByIdUseCase = mock<LoadSingleLocationByIdUseCase>()
    val loadMultipleCharactersUseCase = mock<LoadMultipleCharactersUseCase>()

    val dispatcher = TestCoroutineDispatcher()
    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    @AfterEach
    fun afterEach() {
        Mockito.reset(loadSingleLocationByIdUseCase)
        Mockito.reset(loadMultipleCharactersUseCase)
    }

    @Test
    fun `should load single location`() = runBlocking(){

        val testId = 1

        val viewModel = LocationDetailsViewModel(
            loadSingleLocationByIdUseCase, loadMultipleCharactersUseCase
        )

        viewModel.loadLocationById(locationId = testId)

        val expected = loadSingleLocationByIdUseCase.loadLocationById(testId).map { it.data }
        val actual = viewModel.singleLocation.value

        Assertions.assertEquals(expected, actual)
    }
}