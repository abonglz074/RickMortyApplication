package com.mytestprogram.rickmortyapplication.domain.usecases

import com.mytestprogram.rickmortyapplication.data.models.characters.SingleCharacterEntity
import com.mytestprogram.rickmortyapplication.domain.repository.CharactersRepository
import com.mytestprogram.rickmortyapplication.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadMultipleCharactersUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {

    fun loadMultipleCharacters(characterIds: List<Int>): Flow<Resource<List<SingleCharacterEntity>>> {
        return charactersRepository.loadMultipleCharacters(characterIds)
    }
}