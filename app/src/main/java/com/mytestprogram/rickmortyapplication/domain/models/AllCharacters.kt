package com.mytestprogram.rickmortyapplication.domain.models

data class AllCharacters(
    val info: Info,
    val results: List<SingleCharacter>
)