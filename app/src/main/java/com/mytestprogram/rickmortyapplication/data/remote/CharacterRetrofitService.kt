package com.mytestprogram.rickmortyapplication.data.remote

import com.mytestprogram.rickmortyapplication.domain.models.characters.AllCharacters
import com.mytestprogram.rickmortyapplication.domain.models.characters.SingleCharacter
import com.mytestprogram.rickmortyapplication.domain.models.episodes.AllEpisodes
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterRetrofitService {


    @GET("character")
    suspend fun loadAllCharacters(): AllCharacters

    @GET("character/{character-id}")
    suspend fun loadCharacterById(@Path("character-id") characterId: Int): SingleCharacter

    @GET("episode")
    suspend fun loadAllEpisodes(): AllEpisodes

    @GET("episode/{episode-id}")
    suspend fun loadEpisodeById(@Path("episode-id") episodeId: Int): SingleEpisode
}