package com.mytestprogram.rickmortyapplication.data

import com.mytestprogram.rickmortyapplication.data.remote.CharacterRetrofitService
import com.mytestprogram.rickmortyapplication.domain.repository.CharactersRepository
import com.mytestprogram.rickmortyapplication.domain.models.characters.AllCharacters
import com.mytestprogram.rickmortyapplication.domain.models.characters.SingleCharacter
import com.mytestprogram.rickmortyapplication.domain.models.episodes.AllEpisodes
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val characterRetrofitService: CharacterRetrofitService
) : CharactersRepository {

    override suspend fun loadAllCharacters(): AllCharacters =
        characterRetrofitService.loadAllCharacters()

    override suspend fun loadCharacterById(characterId: Int): SingleCharacter =
        characterRetrofitService.loadCharacterById(characterId)

    override suspend fun loadAllEpisodes(): AllEpisodes =
        characterRetrofitService.loadAllEpisodes()

    override suspend fun loadEpisodesById(episodeId: Int): SingleEpisode =
        characterRetrofitService.loadEpisodeById(episodeId)
}