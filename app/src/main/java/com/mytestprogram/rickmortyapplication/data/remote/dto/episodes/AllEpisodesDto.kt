package com.mytestprogram.rickmortyapplication.data.remote.dto.episodes

import com.mytestprogram.rickmortyapplication.data.local.entities.episodes.AllEpisodesEntity

data class AllEpisodesDto(
    val info: InfoDto,
    val results: List<SingleEpisodeDto>
) {
    fun toAllEpisodesEntity(): AllEpisodesEntity {
        return AllEpisodesEntity(
            info.toInfoEntity(),
            results.map { it.toSingleEpisodeEntity() }
        )
    }
}