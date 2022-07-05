package com.mytestprogram.rickmortyapplication.presentation.list_characters_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mytestprogram.rickmortyapplication.domain.usecases.characters.FilterCharactersUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.characters.LoadAllCharactersUseCase

class CharactersViewModelFactory(
    private val loadAllCharactersUseCase: LoadAllCharactersUseCase,
    private val filterCharactersUseCase: FilterCharactersUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListCharactersViewModel(
            loadAllCharactersUseCase,
            filterCharactersUseCase
        ) as T
    }
}