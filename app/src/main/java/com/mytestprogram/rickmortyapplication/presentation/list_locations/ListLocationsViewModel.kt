package com.mytestprogram.rickmortyapplication.presentation.list_locations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mytestprogram.rickmortyapplication.domain.models.episodes.AllEpisodes
import com.mytestprogram.rickmortyapplication.domain.models.locations.AllLocations
import com.mytestprogram.rickmortyapplication.domain.usecases.LoadAllEpisodesUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.LoadAllLocationsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListLocationsViewModel @Inject constructor(
    private val loadAllLocationsUseCase: LoadAllLocationsUseCase
): ViewModel() {

    private val _locationsList = MutableLiveData<AllLocations>()
    val locationsList: LiveData<AllLocations> = _locationsList

    private val _isDataLoading = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean> = _isDataLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    init {
        loadAllLocations()
    }

    private fun loadAllLocations() {
        _isDataLoading.value = true
        _isError.value = false
        viewModelScope.launch {
            try {
                val loadedAllLocations = loadAllLocationsUseCase.loadAllLocations()
                _locationsList.postValue(loadedAllLocations)
                _isDataLoading.value = false
                _isError.value = false

            } catch (e: Exception) {
                _isDataLoading.value = false
                _isError.value = true
            }
        }

    }
}