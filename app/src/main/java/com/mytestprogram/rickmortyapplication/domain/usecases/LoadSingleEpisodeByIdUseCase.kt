package com.mytestprogram.rickmortyapplication.domain.usecases

import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode
import com.mytestprogram.rickmortyapplication.domain.repository.CharactersRepository
import javax.inject.Inject

class LoadSingleEpisodeByIdUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    suspend fun loadEpisodeById(episodeId: Int): SingleEpisode {
        return charactersRepository.loadEpisodesById(episodeId)
    }
}