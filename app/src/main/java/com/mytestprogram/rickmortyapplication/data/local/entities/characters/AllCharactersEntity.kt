package com.mytestprogram.rickmortyapplication.data.local.entities.characters

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

data class AllCharactersEntity(
    val info: InfoEntity,
    val results: List<SingleCharacterEntity>
)