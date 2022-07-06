package com.mytestprogram.rickmortyapplication.data.remote


import com.mytestprogram.rickmortyapplication.data.remote.dto.characters.AllCharactersDto
import com.mytestprogram.rickmortyapplication.data.remote.dto.characters.SingleCharacterDto
import com.mytestprogram.rickmortyapplication.data.remote.dto.episodes.AllEpisodesDto
import com.mytestprogram.rickmortyapplication.data.remote.dto.episodes.SingleEpisodeDto
import com.mytestprogram.rickmortyapplication.data.remote.dto.locations.AllLocationsDto
import com.mytestprogram.rickmortyapplication.data.remote.dto.locations.SingleLocationDto
import com.mytestprogram.rickmortyapplication.domain.models.episodes.AllEpisodes
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode
import com.mytestprogram.rickmortyapplication.domain.models.locations.AllLocations
import com.mytestprogram.rickmortyapplication.domain.models.locations.SingleLocation
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterRetrofitService {


    @GET("character")
    suspend fun loadAllCharacters(): AllCharactersDto

    @GET("character/{character-id}")
    suspend fun loadCharacterById(@Path("character-id") characterId: Int): SingleCharacterDto

    @GET("character")
    suspend fun filterCharacterByName(@Query("name") characterName: String): AllCharactersDto

    @GET("character")
    suspend fun filterCharactersByStatus(@Query("status") characterStatus: String): AllCharactersDto

    @GET("character")
    suspend fun filterCharactersByGender(@Query("gender") characterGender: String): AllCharactersDto

    @GET("character")
    suspend fun filterCharactersBySpecies(@Query("species") characterSpecies: String): AllCharactersDto

    @GET("character/{character-id}")
    suspend fun loadMultipleCharactersById(@Path("character-id") characterIds: List<Int>): List<SingleCharacterDto>

    @GET("episode")
    suspend fun loadAllEpisodes(): AllEpisodesDto

    @GET("episode/{episode-id}")
    suspend fun loadEpisodeById(@Path("episode-id") episodeId: Int): SingleEpisodeDto

    @GET("episode/{episode-id}")
    suspend fun loadMultipleEpisodesById(@Path("episode-id") episodeIds: List<Int>): List<SingleEpisodeDto>

    @GET("episode")
    suspend fun filterEpisodesByName(@Query("name") episodeName: String): AllEpisodesDto

    @GET("episode")
    suspend fun filterEpisodesBySeason(@Query("episode") episode: List<String>): AllEpisodesDto

    @GET("location")
    suspend fun loadAllLocations(): AllLocationsDto

    @GET("location/{location-id}")
    suspend fun loadLocationById(@Path("location-id") locationId: Int): SingleLocationDto

    @GET("location")
    suspend fun filterLocations(@Query("name") locationName: String): AllLocationsDto

    @GET("location")
    suspend fun filterLocationsByType(@Query("type") locationType: String): AllLocationsDto

    @GET("location")
    suspend fun filterLocationsByDimension(@Query("dimension") locationDimension: String): AllLocationsDto

}