package com.mytestprogram.rickmortyapplication.domain.models.characters

import androidx.room.PrimaryKey
import com.mytestprogram.rickmortyapplication.data.models.characters.Location
import com.mytestprogram.rickmortyapplication.data.models.characters.Origin

data class SingleCharacter(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)