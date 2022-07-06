package com.mytestprogram.rickmortyapplication.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mytestprogram.rickmortyapplication.data.local.entities.characters.AllCharactersEntity
import com.mytestprogram.rickmortyapplication.data.local.entities.characters.SingleCharacterEntity
import com.mytestprogram.rickmortyapplication.data.local.entities.episodes.SingleEpisodeEntity
import com.mytestprogram.rickmortyapplication.data.local.entities.locations.SingleLocationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SingleCharacterDao {

    @Query("SELECT * FROM characters")
    suspend fun getAllCharacters() : List<SingleCharacterEntity>

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getCharacterById(id: Int): SingleCharacterEntity

    @Query("SELECT * FROM characters WHERE name LIKE :name")
    suspend fun getCharacterByName(name: String): List<SingleCharacterEntity>

    @Query("SELECT * FROM characters WHERE status LIKE :status")
    suspend fun getCharacterByStatus(status: String): List<SingleCharacterEntity>

    @Query("SELECT * FROM characters WHERE gender LIKE :gender")
    suspend fun getCharacterByGender(gender: String): List<SingleCharacterEntity>

    @Query("SELECT * FROM characters WHERE species LIKE :species")
    suspend fun getCharacterBySpecies(species: String): List<SingleCharacterEntity>

    @Query("SELECT * FROM characters WHERE id IN (:id)")
    suspend fun getMultipleCharactersById(id: List<Int>): List<SingleCharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(characters: List<SingleCharacterEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: SingleCharacterEntity)

    @Query("DELETE FROM characters")
    suspend fun deleteAllCharacters()

    @Query("DELETE FROM characters WHERE id = :id")
    suspend fun deleteCharacterById(id: Int)

    @Query("DELETE FROM characters WHERE id = :id")
    suspend fun deleteMultipleCharacterById(id: List<Int>)

    @Query("SELECT * FROM episodes")
    suspend fun getAllEpisodes() : List<SingleEpisodeEntity>

    @Query("SELECT * FROM episodes WHERE id = :id")
    suspend fun getEpisodeById(id: Int): SingleEpisodeEntity

    @Query("SELECT * FROM episodes WHERE id IN (:id)")
    suspend fun getMultipleEpisodesById(id: List<Int>): List<SingleEpisodeEntity>

    @Query("SELECT * FROM episodes WHERE name LIKE :name")
    suspend fun getEpisodeByName(name: String): List<SingleEpisodeEntity>

    @Query("SELECT * FROM episodes WHERE episode IN (:episodes)")
    suspend fun getEpisodeBySeason(episodes: List<String>): List<SingleEpisodeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEpisodes(episodes: List<SingleEpisodeEntity>)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertEpisode(episode: SingleEpisodeEntity)

    @Query("DELETE FROM episodes")
    suspend fun deleteAllEpisodes()

//    @Query("DELETE FROM episodes WHERE id = :id")
//    suspend fun deleteAllEpisodes(id: Int)
//
//    @Query("DELETE FROM episodes WHERE id = :id")
//    suspend fun deleteMultipleEpisodes(id: List<Int>)

    @Query("SELECT * FROM locations")
    suspend fun getAllLocations() : List<SingleLocationEntity>

    @Query("SELECT * FROM locations WHERE id = :id")
    suspend fun getLocationsById(id: Int): SingleLocationEntity

    @Query("SELECT * FROM locations WHERE name LIKE :name")
    suspend fun getLocationsByName(name: String): List<SingleLocationEntity>

    @Query("SELECT * FROM locations WHERE type LIKE :type")
    suspend fun getLocationsByType(type: String): List<SingleLocationEntity>

    @Query("SELECT * FROM locations WHERE dimension LIKE :dimension")
    suspend fun getLocationsByDimension(dimension: String): List<SingleLocationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLocations(locations: List<SingleLocationEntity>)

    @Query("DELETE FROM locations")
    suspend fun deleteAllLocations()

}