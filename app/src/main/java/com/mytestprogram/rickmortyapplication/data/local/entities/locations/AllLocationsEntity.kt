package com.mytestprogram.rickmortyapplication.data.local.entities.locations

import com.mytestprogram.rickmortyapplication.data.local.entities.characters.InfoEntity

data class AllLocationsEntity(
    val info: InfoEntity,
    val results: List<SingleLocationEntity>
)