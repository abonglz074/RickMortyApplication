package com.mytestprogram.rickmortyapplication.domain.usecases.locations

import com.mytestprogram.rickmortyapplication.domain.models.characters.SingleCharacter
import com.mytestprogram.rickmortyapplication.domain.models.episodes.AllEpisodes
import com.mytestprogram.rickmortyapplication.domain.models.locations.AllLocations
import com.mytestprogram.rickmortyapplication.domain.models.locations.SingleLocation
import com.mytestprogram.rickmortyapplication.domain.repository.CharactersRepository
import com.mytestprogram.rickmortyapplication.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadAllLocationsUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {

    fun loadAllLocations(): Flow<Resource<List<SingleLocation>>> {
        return charactersRepository.loadAllLocations()
    }
}