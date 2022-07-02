package com.mytestprogram.rickmortyapplication.domain.repository

import com.mytestprogram.rickmortyapplication.data.models.characters.SingleCharacterEntity
import com.mytestprogram.rickmortyapplication.domain.models.episodes.AllEpisodes
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode
import com.mytestprogram.rickmortyapplication.domain.models.locations.AllLocations
import com.mytestprogram.rickmortyapplication.domain.models.locations.SingleLocation
import com.mytestprogram.rickmortyapplication.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    fun loadAllCharacters(): Flow<Resource<List<SingleCharacterEntity>>>

    fun loadCharacterById(characterId: Int): Flow<Resource<SingleCharacterEntity>>

    fun loadMultipleCharacters(characterIds: List<Int>): Flow<Resource<List<SingleCharacterEntity>>>

    suspend fun loadAllEpisodes(): AllEpisodes

    suspend fun loadEpisodesById(episodeId: Int): SingleEpisode

    suspend fun loadMultipleEpisodes(episodeIds: List<Int>): List<SingleEpisode>

    suspend fun loadAllLocations(): AllLocations

    suspend fun loadLocationById(locationId: Int): SingleLocation
}