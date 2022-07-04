package com.mytestprogram.rickmortyapplication.data.remote.dto.characters

import com.mytestprogram.rickmortyapplication.data.local.entities.characters.AllCharactersEntity

data class AllCharactersDto(
    val info: InfoDto,
    val results: List<SingleCharacterDto>
) {
    fun toAllCharactersEntity(): AllCharactersEntity {
        return AllCharactersEntity(
            info = info.toInfoEntity(),
            results = results.map { it.toSingleCharacterEntity() }
        )
    }
}