package com.mytestprogram.rickmortyapplication.domain

import com.mytestprogram.rickmortyapplication.domain.models.AllCharacters
import com.mytestprogram.rickmortyapplication.domain.models.SingleCharacter

interface CharactersRepository {

    suspend fun loadAllCharacters(): AllCharacters

    suspend fun loadCharacterById(characterId: Int): SingleCharacter
}