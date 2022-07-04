package com.mytestprogram.rickmortyapplication.data.local

import androidx.room.TypeConverter
import com.mytestprogram.rickmortyapplication.data.models.characters.Origin

class OriginConverter {

    @TypeConverter
    fun fromOrigin(origin: Origin): String {
        return origin.name
    }
    @TypeConverter
    fun toOrigin(name: String): Origin {
        return Origin(name)
    }
}