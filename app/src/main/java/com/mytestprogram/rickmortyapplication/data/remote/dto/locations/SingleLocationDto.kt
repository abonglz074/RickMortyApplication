package com.mytestprogram.rickmortyapplication.data.remote.dto.locations

import com.mytestprogram.rickmortyapplication.data.local.entities.locations.SingleLocationEntity

data class SingleLocationDto(
    val created: String,
    val dimension: String,
    val id: Int,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String
){
    fun toSingleLocationEntity(): SingleLocationEntity {
        return SingleLocationEntity(
            created, dimension, id, name, residents, type, url
        )
    }
}