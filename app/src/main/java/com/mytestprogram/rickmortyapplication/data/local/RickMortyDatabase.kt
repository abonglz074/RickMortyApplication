package com.mytestprogram.rickmortyapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mytestprogram.rickmortyapplication.data.local.entities.characters.AllCharactersEntity
import com.mytestprogram.rickmortyapplication.data.local.typeconverters.CharacterConverter
import com.mytestprogram.rickmortyapplication.data.local.typeconverters.LocationConverter
import com.mytestprogram.rickmortyapplication.data.local.typeconverters.OriginConverter
import com.mytestprogram.rickmortyapplication.data.local.entities.characters.SingleCharacterEntity
import com.mytestprogram.rickmortyapplication.data.local.entities.episodes.SingleEpisodeEntity
import com.mytestprogram.rickmortyapplication.data.local.entities.locations.SingleLocationEntity


@Database(
    entities = [SingleCharacterEntity::class, SingleLocationEntity::class, SingleEpisodeEntity::class],
    version = 1
)
@TypeConverters(CharacterConverter::class, LocationConverter::class, OriginConverter::class)
abstract class RickMortyDatabase : RoomDatabase() {

    abstract fun getDatabase(): SingleCharacterDao
}