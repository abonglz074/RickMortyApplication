package com.mytestprogram.rickmortyapplication.data.models.characters

data class AllCharactersEntity(
    val info: Info,
    val results: List<SingleCharacterEntity>
)