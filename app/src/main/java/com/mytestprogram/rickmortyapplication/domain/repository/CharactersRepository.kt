package com.mytestprogram.rickmortyapplication.domain.repository

import com.mytestprogram.rickmortyapplication.data.local.entities.characters.SingleCharacterEntity
import com.mytestprogram.rickmortyapplication.domain.models.characters.SingleCharacter
import com.mytestprogram.rickmortyapplication.domain.models.episodes.AllEpisodes
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode
import com.mytestprogram.rickmortyapplication.domain.models.locations.AllLocations
import com.mytestprogram.rickmortyapplication.domain.models.locations.SingleLocation
import com.mytestprogram.rickmortyapplication.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    fun loadAllCharacters(): Flow<Resource<List<SingleCharacter>>>

    fun loadCharacterById(characterId: Int): Flow<Resource<SingleCharacter>>

    fun filterCharacterByName(characterName: String): Flow<Resource<List<SingleCharacter>>>

    fun filterCharacterByStatus(status: String): Flow<Resource<List<SingleCharacter>>>

    fun filterCharacterByGender(gender: String): Flow<Resource<List<SingleCharacter>>>

    fun loadMultipleCharacters(characterIds: List<Int>): Flow<Resource<List<SingleCharacter>>>

    fun loadAllEpisodes(): Flow<Resource<List<SingleEpisode>>>

    fun loadEpisodesById(episodeId: Int): Flow<Resource<SingleEpisode>>

    fun filterEpisodeByName(episodeName: String): Flow<Resource<List<SingleEpisode>>>

    fun loadMultipleEpisodes(episodeIds: List<Int>): Flow<Resource<List<SingleEpisode>>>

    fun loadAllLocations(): Flow<Resource<List<SingleLocation>>>

    fun loadLocationById(locationId: Int): Flow<Resource<SingleLocation>>

    fun filterLocationByName(locationName: String): Flow<Resource<List<SingleLocation>>>
}