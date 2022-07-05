package com.mytestprogram.rickmortyapplication.domain.usecases.characters

import com.mytestprogram.rickmortyapplication.domain.models.characters.SingleCharacter
import com.mytestprogram.rickmortyapplication.domain.repository.CharactersRepository
import com.mytestprogram.rickmortyapplication.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilterCharactersUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
){
    fun filterCharacterByName(characterName: String): Flow<Resource<List<SingleCharacter>>> {
        return charactersRepository.filterCharacterByName(characterName)
    }
}