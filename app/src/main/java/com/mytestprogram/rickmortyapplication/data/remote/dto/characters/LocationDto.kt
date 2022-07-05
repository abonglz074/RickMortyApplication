package com.mytestprogram.rickmortyapplication.data.remote.dto.characters

import com.mytestprogram.rickmortyapplication.data.local.entities.characters.LocationEntity

data class LocationDto(
    val name: String,
    val url: String
){
    fun toLocationEntity(): LocationEntity {
        return LocationEntity(name, url)
    }
}