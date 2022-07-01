package com.mytestprogram.rickmortyapplication.domain.usecases

import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode
import com.mytestprogram.rickmortyapplication.domain.models.locations.SingleLocation
import com.mytestprogram.rickmortyapplication.domain.repository.CharactersRepository
import javax.inject.Inject

class LoadSingleLocationByIdUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    suspend fun loadLocationById(locationId: Int): SingleLocation {
        return charactersRepository.loadLocationById(locationId)
    }
}