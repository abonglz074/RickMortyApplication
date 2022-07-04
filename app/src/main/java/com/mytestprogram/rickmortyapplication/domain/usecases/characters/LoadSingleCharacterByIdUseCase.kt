package com.mytestprogram.rickmortyapplication.domain.usecases

import com.mytestprogram.rickmortyapplication.data.local.entities.characters.SingleCharacterEntity
import com.mytestprogram.rickmortyapplication.domain.models.characters.SingleCharacter
import com.mytestprogram.rickmortyapplication.domain.repository.CharactersRepository
import com.mytestprogram.rickmortyapplication.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadSingleCharacterByIdUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    fun loadCharacterById(characterId: Int): Flow<Resource<SingleCharacter>> {
        return charactersRepository.loadCharacterById(characterId)
    }
}