package com.mytestprogram.rickmortyapplication.presentation.list_characters_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mytestprogram.rickmortyapplication.domain.usecases.characters.LoadAllCharactersUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.characters.LoadSingleCharacterByName

class CharactersViewModelFactory(
    private val loadAllCharactersUseCase: LoadAllCharactersUseCase,
    private val loadSingleCharacterByName: LoadSingleCharacterByName
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListCharactersViewModel(
            loadAllCharactersUseCase,
            loadSingleCharacterByName
        ) as T
    }
}