package com.mytestprogram.rickmortyapplication.domain.usecases.locations

import com.mytestprogram.rickmortyapplication.domain.models.locations.SingleLocation
import com.mytestprogram.rickmortyapplication.domain.repository.CharactersRepository
import com.mytestprogram.rickmortyapplication.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadAllLocationsUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {

    fun loadAllLocations(page: Int): Flow<Resource<List<SingleLocation>>> {
        return charactersRepository.loadAllLocations(page)
    }
}