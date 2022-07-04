package com.mytestprogram.rickmortyapplication.data.remote.dto.locations

import com.mytestprogram.rickmortyapplication.data.local.entities.characters.InfoEntity

data class InfoDto(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: Any
) {
    fun toInfoEntity(): InfoEntity {
        return InfoEntity(
            count, next, pages, prev.toString()
        )
    }
}