package com.mytestprogram.rickmortyapplication.data.local.entities.locations

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mytestprogram.rickmortyapplication.domain.models.locations.SingleLocation

@Entity(tableName = "locations")
data class SingleLocationEntity(
    val created: String,
    val dimension: String,
    @PrimaryKey
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
){
    fun toSingleLocation(): SingleLocation {
        return SingleLocation(
            created, dimension, id, name, residents, type, url
        )
    }
}