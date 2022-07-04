package com.mytestprogram.rickmortyapplication.data.remote.dto.episodes

import com.mytestprogram.rickmortyapplication.data.local.entities.episodes.SingleEpisodeEntity

data class SingleEpisodeDto(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val id: Int,
    val name: String,
    val url: String
){
    fun toSingleEpisodeEntity(): SingleEpisodeEntity {
        return SingleEpisodeEntity(
            air_date, characters, created, episode, id, name, url
        )
    }
}