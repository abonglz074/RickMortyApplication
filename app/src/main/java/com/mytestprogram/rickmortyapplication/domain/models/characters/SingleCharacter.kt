package com.mytestprogram.rickmortyapplication.domain.models.characters

import com.mytestprogram.rickmortyapplication.data.local.entities.characters.LocationEntity
import com.mytestprogram.rickmortyapplication.data.local.entities.characters.OriginEntity

data class SingleCharacter(
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
)