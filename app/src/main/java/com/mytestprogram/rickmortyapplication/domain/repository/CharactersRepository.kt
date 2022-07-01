package com.mytestprogram.rickmortyapplication.domain.repository

import com.mytestprogram.rickmortyapplication.domain.models.characters.AllCharacters
import com.mytestprogram.rickmortyapplication.domain.models.characters.SingleCharacter
import com.mytestprogram.rickmortyapplication.domain.models.episodes.AllEpisodes
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode
import com.mytestprogram.rickmortyapplication.domain.models.locations.AllLocations
import com.mytestprogram.rickmortyapplication.domain.models.locations.SingleLocation

interface CharactersRepository {

    suspend fun loadAllCharacters(): AllCharacters

    suspend fun loadCharacterById(characterId: Int): SingleCharacter

    suspend fun loadMultipleCharacters(characterIds: List<Int>): List<SingleCharacter>

    suspend fun loadAllEpisodes(): AllEpisodes

    suspend fun loadEpisodesById(episodeId: Int): SingleEpisode

    suspend fun loadMultipleEpisodes(episodeIds: List<Int>): List<SingleEpisode>

    suspend fun loadAllLocations(): AllLocations

    suspend fun loadLocationById(locationId: Int): SingleLocation
}