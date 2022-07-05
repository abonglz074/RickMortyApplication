package com.mytestprogram.rickmortyapplication.data.remote.dto.characters

import com.mytestprogram.rickmortyapplication.data.local.entities.characters.OriginEntity

data class OriginDto(
    val name: String,
    val url: String
){
    fun toOriginEntity(): OriginEntity {
        return OriginEntity(
            name, url
        )
    }
}