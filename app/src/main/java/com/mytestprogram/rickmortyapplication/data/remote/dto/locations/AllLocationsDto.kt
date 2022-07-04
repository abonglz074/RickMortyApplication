package com.mytestprogram.rickmortyapplication.data.remote.dto.locations

import com.mytestprogram.rickmortyapplication.data.local.entities.locations.AllLocationsEntity

data class AllLocationsDto(
    val info: InfoDto,
    val results: List<SingleLocationDto>
){
    fun toAllLocationsEntity(): AllLocationsEntity {
        return AllLocationsEntity(
            info.toInfoEntity(),
            results.map { it.toSingleLocationEntity() }
        )
    }
}