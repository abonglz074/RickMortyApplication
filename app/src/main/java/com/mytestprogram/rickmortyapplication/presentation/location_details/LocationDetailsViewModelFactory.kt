package com.mytestprogram.rickmortyapplication.presentation.location_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mytestprogram.rickmortyapplication.domain.usecases.LoadMultipleCharactersUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.LoadSingleEpisodeByIdUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.LoadSingleLocationByIdUseCase
import com.mytestprogram.rickmortyapplication.presentation.episode_details.EpisodeDetailsViewModel

class LocationDetailsViewModelFactory(
    val loadSingleLocationByIdUseCase: LoadSingleLocationByIdUseCase,
    val loadMultipleCharactersUseCase: LoadMultipleCharactersUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LocationDetailsViewModel(
            loadSingleLocationByIdUseCase,
            loadMultipleCharactersUseCase
        ) as T
    }
}