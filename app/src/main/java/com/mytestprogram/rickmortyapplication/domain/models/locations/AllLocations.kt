package com.mytestprogram.rickmortyapplication.domain.models.locations

data class AllLocations(
    val info: Info,
    val results: List<SingleLocation>
)