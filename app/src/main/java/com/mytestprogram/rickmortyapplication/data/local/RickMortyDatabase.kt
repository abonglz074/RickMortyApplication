package com.mytestprogram.rickmortyapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mytestprogram.rickmortyapplication.data.models.characters.SingleCharacterEntity


@Database(entities = [SingleCharacterEntity::class], version = 3)
@TypeConverters(CharacterConverter::class, LocationConverter::class, OriginConverter::class)
abstract class RickMortyDatabase: RoomDatabase() {

    abstract fun getDatabase(): SingleCharacterDao
}