package com.mytestprogram.rickmortyapplication.domain.usecases

import com.mytestprogram.rickmortyapplication.domain.repository.CharactersRepository
import com.mytestprogram.rickmortyapplication.domain.models.characters.AllCharacters
import javax.inject.Inject

class LoadAllCharactersUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {

    suspend fun loadAllCharacters(): AllCharacters {
        return charactersRepository.loadAllCharacters()
    }
}