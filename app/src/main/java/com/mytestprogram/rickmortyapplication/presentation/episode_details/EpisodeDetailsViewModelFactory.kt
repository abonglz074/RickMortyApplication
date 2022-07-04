package com.mytestprogram.rickmortyapplication.presentation.episode_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mytestprogram.rickmortyapplication.domain.usecases.characters.LoadMultipleCharactersUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.episodes.LoadSingleEpisodeByIdUseCase

class EpisodeDetailsViewModelFactory(
    val loadSingleEpisodeByIdUseCase: LoadSingleEpisodeByIdUseCase,
    val loadMultipleCharactersUseCase: LoadMultipleCharactersUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EpisodeDetailsViewModel(
            loadSingleEpisodeByIdUseCase,
            loadMultipleCharactersUseCase
        ) as T
    }
}