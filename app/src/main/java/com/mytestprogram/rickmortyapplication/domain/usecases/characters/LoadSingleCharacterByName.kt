package com.mytestprogram.rickmortyapplication.domain.usecases.characters

import com.mytestprogram.rickmortyapplication.domain.models.characters.SingleCharacter
import com.mytestprogram.rickmortyapplication.domain.repository.CharactersRepository
import com.mytestprogram.rickmortyapplication.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadSingleCharacterByName @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    fun loadCharacterByName(characterName: String): Flow<Resource<SingleCharacter>> {
        return charactersRepository.loadCharacterByName(characterName)
    }
}