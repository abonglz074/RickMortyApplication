package com.mytestprogram.rickmortyapplication.presentation.episode_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mytestprogram.rickmortyapplication.domain.models.characters.SingleCharacter
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode
import com.mytestprogram.rickmortyapplication.domain.usecases.characters.LoadMultipleCharactersUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.episodes.LoadSingleEpisodeByIdUseCase
import com.mytestprogram.rickmortyapplication.utils.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodeDetailsViewModel @Inject constructor(
    val loadSingleEpisodeByIdUseCase: LoadSingleEpisodeByIdUseCase,
    val loadMultipleCharactersUseCase: LoadMultipleCharactersUseCase
): ViewModel() {

    private val _singleEpisode = MutableLiveData<SingleEpisode?>()
    val singleEpisode: LiveData<SingleEpisode?> = _singleEpisode

    private val _charactersList = MutableLiveData<List<SingleCharacter>?>()
    val charactersList: LiveData<List<SingleCharacter>?> = _charactersList

    private val _isDataLoading = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean> = _isDataLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError


    fun loadEpisodeById(episodeId: Int) {
        viewModelScope.launch {
            loadSingleEpisodeByIdUseCase.loadEpisodeById(episodeId).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _singleEpisode.postValue(result.data)
                        _isDataLoading.value = false
                        _isError.postValue(false)
                    }
                    is Resource.Error -> {
                        _isError.value = true
                    }
                    is Resource.Loading -> {
                        _isDataLoading.value = true
                    }
                }
            }
        }
    }
    fun loadMultipleCharacters(characterIds: List<Int>) {
        viewModelScope.launch {
            loadMultipleCharactersUseCase.loadMultipleCharacters(characterIds).collectLatest { result ->
                when(result) {
                    is Resource.Success -> {
                        _charactersList.postValue(result.data)
                    }
                }
            }
        }
    }


}