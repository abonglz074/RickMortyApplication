package com.mytestprogram.rickmortyapplication.presentation.character_details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mytestprogram.rickmortyapplication.domain.usecases.LoadSingleCharacterByIdUseCase

class CharacterDetailsViewModelFactory(
     val loadSingleCharacterByIdUseCase: LoadSingleCharacterByIdUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CharacterDetailsViewModel(
            loadSingleCharacterByIdUseCase
        ) as T
    }
}