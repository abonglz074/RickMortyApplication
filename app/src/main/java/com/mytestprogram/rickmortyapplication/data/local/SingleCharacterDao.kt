package com.mytestprogram.rickmortyapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mytestprogram.rickmortyapplication.data.models.characters.SingleCharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SingleCharacterDao {

    @Query("SELECT * FROM characters")
    fun getAllCharacters() : Flow<List<SingleCharacterEntity>>

    @Query("SELECT * FROM characters WHERE id = :id")
    fun getCharacterById(id: Int): Flow<SingleCharacterEntity>

    @Query("SELECT * FROM characters WHERE id = :id")
    fun getMultipleCharactersById(id: List<Int>): Flow<List<SingleCharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<SingleCharacterEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: SingleCharacterEntity)

    @Query("DELETE FROM characters")
    suspend fun deleteAllCharacters()

    @Query("DELETE FROM characters WHERE id = :id")
    suspend fun deleteCharacterById(id: Int)

    @Query("DELETE FROM characters WHERE id = :id")
    suspend fun deleteMultipleCharacterById(id: List<Int>)
}