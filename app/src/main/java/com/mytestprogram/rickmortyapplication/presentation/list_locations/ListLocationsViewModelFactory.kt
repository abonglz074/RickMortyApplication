package com.mytestprogram.rickmortyapplication.presentation.list_locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mytestprogram.rickmortyapplication.domain.usecases.locations.LoadAllLocationsUseCase

class ListLocationsViewModelFactory(
    private val loadAllLocationsUseCase: LoadAllLocationsUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListLocationsViewModel(
            loadAllLocationsUseCase
        ) as T
    }
}