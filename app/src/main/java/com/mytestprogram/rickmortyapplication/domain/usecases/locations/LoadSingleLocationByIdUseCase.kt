package com.mytestprogram.rickmortyapplication.domain.usecases.locations

import com.mytestprogram.rickmortyapplication.domain.models.characters.SingleCharacter
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode
import com.mytestprogram.rickmortyapplication.domain.models.locations.SingleLocation
import com.mytestprogram.rickmortyapplication.domain.repository.CharactersRepository
import com.mytestprogram.rickmortyapplication.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadSingleLocationByIdUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    fun loadLocationById(locationId: Int): Flow<Resource<SingleLocation>> {
        return charactersRepository.loadLocationById(locationId)
    }
}