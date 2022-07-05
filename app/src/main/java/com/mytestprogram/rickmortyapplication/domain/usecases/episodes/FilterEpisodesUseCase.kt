package com.mytestprogram.rickmortyapplication.domain.usecases.episodes

import com.mytestprogram.rickmortyapplication.domain.models.characters.SingleCharacter
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode
import com.mytestprogram.rickmortyapplication.domain.repository.CharactersRepository
import com.mytestprogram.rickmortyapplication.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilterEpisodesUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
){
    fun filterEpisodesByName(episodeName: String): Flow<Resource<List<SingleEpisode>>> {
        return charactersRepository.filterEpisodeByName(episodeName)
    }
}