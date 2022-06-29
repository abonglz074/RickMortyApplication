package com.mytestprogram.rickmortyapplication.domain.repository

import com.mytestprogram.rickmortyapplication.domain.models.characters.AllCharacters
import com.mytestprogram.rickmortyapplication.domain.models.characters.SingleCharacter
import com.mytestprogram.rickmortyapplication.domain.models.episodes.AllEpisodes
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode

interface CharactersRepository {

    suspend fun loadAllCharacters(): AllCharacters

    suspend fun loadCharacterById(characterId: Int): SingleCharacter

    suspend fun loadAllEpisodes(): AllEpisodes

    suspend fun loadEpisodesById(episodeId: Int): SingleEpisode
}