package com.mytestprogram.rickmortyapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mytestprogram.rickmortyapplication.domain.LoadAllCharactersUseCase

class CharactersViewModelFactory(
    val loadAllCharactersUseCase: LoadAllCharactersUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListCharactersViewModel(
            loadAllCharactersUseCase
        ) as T
    }
}