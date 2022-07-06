package com.mytestprogram.rickmortyapplication.domain.usecases.locations

import com.mytestprogram.rickmortyapplication.domain.models.locations.SingleLocation
import com.mytestprogram.rickmortyapplication.domain.repository.CharactersRepository
import com.mytestprogram.rickmortyapplication.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilterLocationsUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
){
    fun filterLocationsByName(locationName: String): Flow<Resource<List<SingleLocation>>> {
        return charactersRepository.filterLocationByName(locationName)
    }
    fun filterLocationsByType(locationType: String): Flow<Resource<List<SingleLocation>>> {
        return charactersRepository.filterLocationByType(locationType)
    }
    fun filterLocationsByDimension(locationDimension: String): Flow<Resource<List<SingleLocation>>> {
        return charactersRepository.filterLocationByDimension(locationDimension)
    }
}