package com.mytestprogram.rickmortyapplication.data.remote.dto.characters

import com.mytestprogram.rickmortyapplication.data.local.entities.characters.LocationEntity
import com.mytestprogram.rickmortyapplication.data.local.entities.characters.OriginEntity
import com.mytestprogram.rickmortyapplication.data.local.entities.characters.SingleCharacterEntity

data class SingleCharacterDto(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: LocationEntity,
    val name: String,
    val origin: OriginEntity,
    val species: String,
    val status: String,
    val type: String,
    val url: String
) {
    fun toSingleCharacterEntity(): SingleCharacterEntity {
        return SingleCharacterEntity(
            created, episode, gender, id, image, location, name, origin, species, status, type, url
        )
    }
}