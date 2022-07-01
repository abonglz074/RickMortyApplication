package com.mytestprogram.rickmortyapplication.data.remote

import com.mytestprogram.rickmortyapplication.domain.models.characters.AllCharacters
import com.mytestprogram.rickmortyapplication.domain.models.characters.SingleCharacter
import com.mytestprogram.rickmortyapplication.domain.models.episodes.AllEpisodes
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode
import com.mytestprogram.rickmortyapplication.domain.models.locations.AllLocations
import com.mytestprogram.rickmortyapplication.domain.models.locations.SingleLocation
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterRetrofitService {


    @GET("character")
    suspend fun loadAllCharacters(): AllCharacters

    @GET("character/{character-id}")
    suspend fun loadCharacterById(@Path("character-id") characterId: Int): SingleCharacter

    @GET("character/{character-id}")
    suspend fun loadMultipleCharactersById(@Path("character-id") characterIds: List<Int>): List<SingleCharacter>

    @GET("episode")
    suspend fun loadAllEpisodes(): AllEpisodes

    @GET("episode/{episode-id}")
    suspend fun loadEpisodeById(@Path("episode-id") episodeId: Int): SingleEpisode

    @GET("episode/{episode-id}")
    suspend fun loadMultipleEpisodesById(@Path("episode-id") episodeIds: List<Int>): List<SingleEpisode>

    @GET("location")
    suspend fun loadAllLocations(): AllLocations

    @GET("location/{location-id}")
    suspend fun loadLocationById(@Path("location-id") locationId: Int): SingleLocation

}