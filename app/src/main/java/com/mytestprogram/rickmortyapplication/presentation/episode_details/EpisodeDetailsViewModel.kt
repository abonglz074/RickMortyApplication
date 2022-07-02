package com.mytestprogram.rickmortyapplication.presentation.episode_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mytestprogram.rickmortyapplication.data.models.characters.SingleCharacterEntity
import com.mytestprogram.rickmortyapplication.domain.models.characters.SingleCharacter
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode
import com.mytestprogram.rickmortyapplication.domain.usecases.LoadMultipleCharactersUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.LoadSingleEpisodeByIdUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodeDetailsViewModel @Inject constructor(
    val loadSingleEpisodeByIdUseCase: LoadSingleEpisodeByIdUseCase,
    val loadMultipleCharactersUseCase: LoadMultipleCharactersUseCase
): ViewModel() {

    private val _singleEpisode = MutableLiveData<SingleEpisode>()
    val singleEpisode: LiveData<SingleEpisode> = _singleEpisode

    private val _charactersList = MutableLiveData<List<SingleCharacterEntity>>()
    val charactersList: LiveData<List<SingleCharacterEntity>> = _charactersList

    private val _isDataLoading = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean> = _isDataLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError


    fun loadEpisodeById(episodeId: Int) {
        _isDataLoading.value = true
        _isError.value = false
        viewModelScope.launch {
            try {
                val loadedSingleEpisode = loadSingleEpisodeByIdUseCase.loadEpisodeById(episodeId)
                _singleEpisode.postValue(loadedSingleEpisode)
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
//                _charactersList.postValue(loadedMultipleCharacters)
            } catch (e:Exception) {

            }
        }
    }


}