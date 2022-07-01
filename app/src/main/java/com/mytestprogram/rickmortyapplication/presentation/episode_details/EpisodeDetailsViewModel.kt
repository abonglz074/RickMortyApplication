package com.mytestprogram.rickmortyapplication.presentation.episode_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mytestprogram.rickmortyapplication.domain.models.characters.SingleCharacter
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode
import com.mytestprogram.rickmortyapplication.domain.usecases.LoadSingleCharacterByIdUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.LoadSingleEpisodeByIdUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodeDetailsViewModel @Inject constructor(
    val loadSingleEpisodeByIdUseCase: LoadSingleEpisodeByIdUseCase
): ViewModel() {

    private val _singleEpisode = MutableLiveData<SingleEpisode>()
    val singleEpisode: LiveData<SingleEpisode> = _singleEpisode

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


}