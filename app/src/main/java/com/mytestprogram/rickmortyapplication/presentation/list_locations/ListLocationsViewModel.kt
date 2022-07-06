package com.mytestprogram.rickmortyapplication.presentation.list_locations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode
import com.mytestprogram.rickmortyapplication.domain.models.locations.SingleLocation
import com.mytestprogram.rickmortyapplication.domain.usecases.locations.FilterLocationsUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.locations.LoadAllLocationsUseCase
import com.mytestprogram.rickmortyapplication.utils.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListLocationsViewModel @Inject constructor(
    private val loadAllLocationsUseCase: LoadAllLocationsUseCase,
    private val filterLocationsUseCase: FilterLocationsUseCase
): ViewModel() {

    private val _locationsList = MutableLiveData<List<SingleLocation>?>()
    val locationsList: LiveData<List<SingleLocation>?> = _locationsList

    private val _filterLocations = MutableLiveData<List<SingleLocation>>()
    val filterLocations: LiveData<List<SingleLocation>> = _filterLocations

    private val _isDataLoading = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean> = _isDataLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    init {
        loadAllLocations()
    }

    fun loadAllLocations() {
        viewModelScope.launch {
            loadAllLocationsUseCase.loadAllLocations().collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _locationsList.postValue(result.data)
                        _isDataLoading.value = false
                        _isError.postValue(false)
                    }
                    is Resource.Error -> {
                        _locationsList.postValue(result.data)
                        if (result.data.isNullOrEmpty()) {
                            _isError.postValue(true)
                        }
                        _isDataLoading.postValue(false)
                    }
                    is Resource.Loading -> {
                        if (result.data.isNullOrEmpty()) {
                            _isDataLoading.value = false
                        }
                        _isError.postValue(false)
                    }
                }
            }
        }
    }
    fun onSearch(queryName: String) {
        viewModelScope.launch {
            filterLocationsUseCase.filterLocationsByName(queryName).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _filterLocations.postValue(result.data ?: emptyList())
                        _isDataLoading.postValue(false)
                        _isError.postValue(false)
                    }

                }
            }
        }
    }
    fun filterByType(locationType: String) {
        viewModelScope.launch {
            filterLocationsUseCase.filterLocationsByType(locationType).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _filterLocations.postValue(result.data ?: emptyList())
                        _isDataLoading.postValue(false)
                        _isError.postValue(false)
                    }

                }
            }
        }
    }
    fun filterByDimension(locationDimension: String) {
        viewModelScope.launch {
            filterLocationsUseCase.filterLocationsByDimension(locationDimension).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _filterLocations.postValue(result.data ?: emptyList())
                        _isDataLoading.postValue(false)
                        _isError.postValue(false)
                    }

                }
            }
        }
    }
}