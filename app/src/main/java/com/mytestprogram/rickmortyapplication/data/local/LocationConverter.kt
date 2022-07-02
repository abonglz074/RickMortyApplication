package com.mytestprogram.rickmortyapplication.data.local

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.mytestprogram.rickmortyapplication.data.models.characters.Location

class LocationConverter {

    @TypeConverter
    fun fromLocation(location: Location): String {
        return location.name
    }
    @TypeConverter
    fun toLocation(name: String): Location {
        return Location(name)
    }
}