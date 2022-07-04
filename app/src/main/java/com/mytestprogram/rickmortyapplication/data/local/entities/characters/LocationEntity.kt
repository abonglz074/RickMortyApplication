package com.mytestprogram.rickmortyapplication.data.local.entities.characters

import androidx.room.ColumnInfo

data class Location(
    @ColumnInfo(name = "location_name")
    val name: String,
    @ColumnInfo(name = "location_url")
    val url: String
)