package com.mytestprogram.rickmortyapplication.utils

import androidx.fragment.app.Fragment

fun Fragment.navigator() = requireActivity() as Navigation

interface Navigation {

    fun showCharactersList()

    fun showLocationsList()

    fun showEpisodesList()

    fun showCharacterDetails(characterId: Int)

    fun showLocationDetails(locationId: Int)

    fun showEpisodeDetails(episodeId: Int)


}