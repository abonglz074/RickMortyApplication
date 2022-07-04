package com.mytestprogram.rickmortyapplication.data.local.entities.episodes

import com.mytestprogram.rickmortyapplication.data.local.entities.characters.InfoEntity

data class AllEpisodesEntity(
    val info: InfoEntity,
    val results: List<SingleEpisodeEntity>
)