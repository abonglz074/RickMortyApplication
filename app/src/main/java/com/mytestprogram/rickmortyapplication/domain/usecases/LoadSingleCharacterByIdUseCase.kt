package com.mytestprogram.rickmortyapplication.domain.usecases

import com.mytestprogram.rickmortyapplication.domain.models.characters.SingleCharacter
import com.mytestprogram.rickmortyapplication.domain.repository.CharactersRepository
import javax.inject.Inject

class LoadSingleCharacterByIdUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    suspend fun loadCharacterById(characterId: Int): SingleCharacter {
        return charactersRepository.loadCharacterById(characterId)
    }
}