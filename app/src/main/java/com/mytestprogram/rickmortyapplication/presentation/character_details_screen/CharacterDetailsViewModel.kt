package com.mytestprogram.rickmortyapplication.presentation.character_details_screen

import androidx.lifecycle.*
import com.mytestprogram.rickmortyapplication.domain.models.characters.SingleCharacter
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode
import com.mytestprogram.rickmortyapplication.domain.usecases.episodes.LoadMultipleEpisodesUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.characters.LoadSingleCharacterByIdUseCase
import com.mytestprogram.rickmortyapplication.utils.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailsViewModel @Inject constructor(
    val loadSingleCharacterByIdUseCase: LoadSingleCharacterByIdUseCase,
    val loadMultipleEpisodesUseCase: LoadMultipleEpisodesUseCase
): ViewModel() {


    private val _singleCharacter = MutableLiveData<SingleCharacter?>()
    val singleCharacter: LiveData<SingleCharacter?> = _singleCharacter

    private val _episodesList = MutableLiveData<List<SingleEpisode>?>()
    val episodesList: LiveData<List<SingleEpisode>?> = _episodesList

    private val _isDataLoading = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean> = _isDataLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError



    fun loadCharacterById(characterId: Int) {
        viewModelScope.launch {
            loadSingleCharacterByIdUseCase.loadCharacterById(characterId).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _singleCharacter.postValue(result.data)
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
    fun loadMultipleEpisodes(episodeIds: List<Int>) {
        viewModelScope.launch {
            loadMultipleEpisodesUseCase.loadMultipleEpisodes(episodeIds).collectLatest { result ->
                when(result) {
                    is Resource.Success -> {
                        _episodesList.postValue(result.data ?: emptyList())
                        _isDataLoading.value = false
                    }
                    is Resource.Loading -> {
                        if (result.data.isNullOrEmpty()) {
                            _isDataLoading.value = true
                        }
                    }
                    is Resource.Error -> {
                        _isDataLoading.value = false
                    }
                }
            }
        }
    }


}