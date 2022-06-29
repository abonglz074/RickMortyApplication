package com.mytestprogram.rickmortyapplication.domain.usecases

import com.mytestprogram.rickmortyapplication.domain.models.characters.AllCharacters
import com.mytestprogram.rickmortyapplication.domain.models.episodes.AllEpisodes
import com.mytestprogram.rickmortyapplication.domain.repository.CharactersRepository
import javax.inject.Inject

class LoadAllEpisodesUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {

    suspend fun loadAllEpisodes(): AllEpisodes {
        return charactersRepository.loadAllEpisodes()
    }
}