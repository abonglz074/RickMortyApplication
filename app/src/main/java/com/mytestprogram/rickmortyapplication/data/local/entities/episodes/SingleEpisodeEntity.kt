package com.mytestprogram.rickmortyapplication.data.local.entities.episodes

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode


@Entity(tableName = "episodes")
data class SingleEpisodeEntity(
    val air_date: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    @PrimaryKey
    val id: Int,
    val name: String,
    val url: String
) {
    fun toSingleEpisode(): SingleEpisode {
        return SingleEpisode(
            air_date, characters, created, episode, id, name, url
        )
    }
}