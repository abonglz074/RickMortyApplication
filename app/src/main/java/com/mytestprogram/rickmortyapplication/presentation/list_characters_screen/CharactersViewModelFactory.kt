package com.mytestprogram.rickmortyapplication.presentation.list_characters_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mytestprogram.rickmortyapplication.domain.usecases.LoadAllCharactersUseCase

class CharactersViewModelFactory(
    private val loadAllCharactersUseCase: LoadAllCharactersUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListCharactersViewModel(
            loadAllCharactersUseCase
        ) as T
    }
}