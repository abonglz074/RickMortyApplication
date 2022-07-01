package com.mytestprogram.rickmortyapplication.domain.usecases

import com.mytestprogram.rickmortyapplication.domain.models.episodes.AllEpisodes
import com.mytestprogram.rickmortyapplication.domain.models.locations.AllLocations
import com.mytestprogram.rickmortyapplication.domain.repository.CharactersRepository
import javax.inject.Inject

class LoadAllLocationsUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {

    suspend fun loadAllLocations(): AllLocations {
        return charactersRepository.loadAllLocations()
    }
}