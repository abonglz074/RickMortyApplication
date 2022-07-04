package com.mytestprogram.rickmortyapplication.data.local.entities.characters

import androidx.room.ColumnInfo

data class OriginEntity(
    @ColumnInfo(name = "origin_name")
    val name: String,
    @ColumnInfo(name = "origin_url")
    val url: String
)