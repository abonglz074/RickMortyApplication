package com.mytestprogram.rickmortyapplication.domain

import com.mytestprogram.rickmortyapplication.domain.models.AllCharacters
import javax.inject.Inject

class LoadAllCharactersUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {

    suspend fun loadAllCharacters(): AllCharacters {
        return charactersRepository.loadAllCharacters()
    }
}