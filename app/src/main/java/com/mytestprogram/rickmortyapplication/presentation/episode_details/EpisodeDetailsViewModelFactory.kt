package com.mytestprogram.rickmortyapplication.presentation.episode_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mytestprogram.rickmortyapplication.domain.usecases.LoadSingleCharacterByIdUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.LoadSingleEpisodeByIdUseCase
import com.mytestprogram.rickmortyapplication.presentation.character_details_screen.CharacterDetailsViewModel

class EpisodeDetailsViewModelFactory(
    val loadSingleEpisodeByIdUseCase: LoadSingleEpisodeByIdUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EpisodeDetailsViewModel(
            loadSingleEpisodeByIdUseCase
        ) as T
    }
}