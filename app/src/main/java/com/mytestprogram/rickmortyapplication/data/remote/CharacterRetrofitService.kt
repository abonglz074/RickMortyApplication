package com.mytestprogram.rickmortyapplication.data.remote

import com.mytestprogram.rickmortyapplication.domain.models.AllCharacters
import com.mytestprogram.rickmortyapplication.domain.models.SingleCharacter
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterRetrofitService {


    @GET("character")
    suspend fun loadAllCharacters(): AllCharacters

    @GET("character/{character-id}")
    suspend fun loadCharacterById(@Path("character-id") characterId: Int): SingleCharacter
}