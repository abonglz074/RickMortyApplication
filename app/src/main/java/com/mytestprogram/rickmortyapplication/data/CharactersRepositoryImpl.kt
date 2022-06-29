package com.mytestprogram.rickmortyapplication.data

import com.mytestprogram.rickmortyapplication.data.remote.CharacterRetrofitService
import com.mytestprogram.rickmortyapplication.domain.CharactersRepository
import com.mytestprogram.rickmortyapplication.domain.models.AllCharacters
import com.mytestprogram.rickmortyapplication.domain.models.SingleCharacter
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val characterRetrofitService: CharacterRetrofitService
) : CharactersRepository {

    override suspend fun loadAllCharacters(): AllCharacters =
        characterRetrofitService.loadAllCharacters()

    override suspend fun loadCharacterById(characterId: Int): SingleCharacter =
        characterRetrofitService.loadCharacterById(characterId)
}