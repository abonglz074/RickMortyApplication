package com.mytestprogram.rickmortyapplication.presentation.list_episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mytestprogram.rickmortyapplication.domain.usecases.episodes.LoadAllEpisodesUseCase

class ListEpisodesViewModelFactory(
    private val loadAllEpisodesUseCase: LoadAllEpisodesUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListEpisodesViewModel(
            loadAllEpisodesUseCase
        ) as T
    }
}