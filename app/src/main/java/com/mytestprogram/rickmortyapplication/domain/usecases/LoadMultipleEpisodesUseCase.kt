package com.mytestprogram.rickmortyapplication.domain.usecases

import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode
import com.mytestprogram.rickmortyapplication.domain.repository.CharactersRepository
import javax.inject.Inject

class LoadMultipleEpisodesUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {

    suspend fun loadMultipleEpisodes(episodeIds: List<Int>): List<SingleEpisode> {
        return charactersRepository.loadMultipleEpisodes(episodeIds)
    }
}