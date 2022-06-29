package com.mytestprogram.rickmortyapplication.domain.models.episodes

data class AllEpisodes(
    val info: Info,
    val results: List<SingleEpisode>
)