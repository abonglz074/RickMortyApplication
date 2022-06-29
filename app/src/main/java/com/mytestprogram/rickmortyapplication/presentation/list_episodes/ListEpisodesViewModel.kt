package com.mytestprogram.rickmortyapplication.presentation.list_episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mytestprogram.rickmortyapplication.domain.models.episodes.AllEpisodes
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode
import com.mytestprogram.rickmortyapplication.domain.usecases.LoadAllEpisodesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject


class ListEpisodesViewModel @Inject constructor(
    private val loadAllEpisodesUseCase: LoadAllEpisodesUseCase
): ViewModel() {

    private val _episodesList = MutableLiveData<AllEpisodes>()
    val episodesList: LiveData<AllEpisodes> = _episodesList

    private val _isDataLoading = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean> = _isDataLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    init {
        loadAllEpisodes()
    }

    private fun loadAllEpisodes() {
        _isDataLoading.value = true
        _isError.value = false
        viewModelScope.launch {
            try {
                val loadedAllEpisodes = loadAllEpisodesUseCase.loadAllEpisodes()
                _episodesList.postValue(loadedAllEpisodes)
                _isDataLoading.value = false
                _isError.value = false

            } catch (e: Exception) {
                _isDataLoading.value = false
                _isError.value = true
            }
        }

    }
}