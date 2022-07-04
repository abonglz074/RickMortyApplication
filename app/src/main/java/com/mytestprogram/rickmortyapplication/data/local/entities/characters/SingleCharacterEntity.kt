package com.mytestprogram.rickmortyapplication.data.local.entities.characters

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mytestprogram.rickmortyapplication.domain.models.characters.SingleCharacter

@Entity(tableName = "characters")
data class SingleCharacterEntity(
    val created: String,
    val episode: List<String>,
    val gender: String,
    @PrimaryKey
    val id: Int,
    val image: String,
    @Embedded
    val location: LocationEntity,
    val name: String,
    @Embedded
    val origin: OriginEntity,
    val species: String,
    val status: String,
    val type: String,
    val url: String
){
    fun toSingleCharacter(): SingleCharacter {
        return SingleCharacter(
            created, episode, gender, id, image, location, name, origin, species, status, type, url
        )
    }


}