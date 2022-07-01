package com.mytestprogram.rickmortyapplication.presentation.location_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mytestprogram.rickmortyapplication.domain.models.characters.SingleCharacter
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode
import com.mytestprogram.rickmortyapplication.domain.models.locations.SingleLocation
import com.mytestprogram.rickmortyapplication.domain.usecases.LoadMultipleCharactersUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.LoadSingleEpisodeByIdUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.LoadSingleLocationByIdUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationDetailsViewModel @Inject constructor(
    val loadSingleLocationByIdUseCase: LoadSingleLocationByIdUseCase,
    val loadMultipleCharactersUseCase: LoadMultipleCharactersUseCase
): ViewModel() {

    private val _singleLocation = MutableLiveData<SingleLocation>()
    val singleLocation: LiveData<SingleLocation> = _singleLocation

    private val _charactersList = MutableLiveData<List<SingleCharacter>>()
    val charactersList: LiveData<List<SingleCharacter>> = _charactersList

    private val _isDataLoading = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean> = _isDataLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError


    fun loadLocationById(locationId: Int) {
        _isDataLoading.value = true
        _isError.value = false
        viewModelScope.launch {
            try {
                val loadedSingleLocation = loadSingleLocationByIdUseCase.loadLocationById(locationId)
                _singleLocation.postValue(loadedSingleLocation)
                _isDataLoading.value = false
                _isError.value = false
            } catch (e: Exception) {
                _isDataLoading.value = false
                _isError.value = true

            }
        }
    }

    fun loadMultipleCharacters(characterIds: List<Int>) {
        viewModelScope.launch {
            try {
                val loadedMultipleCharacters = loadMultipleCharactersUseCase.loadMultipleCharacters(characterIds)
                _charactersList.postValue(loadedMultipleCharacters)
            } catch (e:Exception) {

            }
        }
    }


}