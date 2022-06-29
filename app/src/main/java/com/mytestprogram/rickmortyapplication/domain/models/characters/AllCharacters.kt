package com.mytestprogram.rickmortyapplication.domain.models.characters

data class AllCharacters(
    val info: Info,
    val results: List<SingleCharacter>
)