package com.mytestprogram.rickmortyapplication.presentation.list_episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mytestprogram.rickmortyapplication.domain.models.characters.SingleCharacter
import com.mytestprogram.rickmortyapplication.domain.models.episodes.SingleEpisode
import com.mytestprogram.rickmortyapplication.domain.usecases.episodes.FilterEpisodesUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.episodes.LoadAllEpisodesUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.episodes.LoadMultipleEpisodesUseCase
import com.mytestprogram.rickmortyapplication.utils.Resource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class ListEpisodesViewModel @Inject constructor(
    private val loadAllEpisodesUseCase: LoadAllEpisodesUseCase,
    private val filterEpisodesUseCase: FilterEpisodesUseCase,
    private val loadMultipleEpisodesUseCase: LoadMultipleEpisodesUseCase
): ViewModel() {

    var page = 1

    private val _episodesList = MutableLiveData<List<SingleEpisode>?>()
    val episodesList: LiveData<List<SingleEpisode>?> = _episodesList

    private val _filterEpisodes = MutableLiveData<List<SingleEpisode>>()
    val filterEpisodes: LiveData<List<SingleEpisode>> = _filterEpisodes

    private val _isDataLoading = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean> = _isDataLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    private val _noData = MutableLiveData<Boolean>()
    val noData: LiveData<Boolean> = _noData

    init {
        loadAllEpisodes()
    }

    fun loadAllEpisodes() {
        viewModelScope.launch {
            loadAllEpisodesUseCase.loadAllEpisodes(page).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _episodesList.postValue(result.data)
                        page++
                        _isDataLoading.value = false
                    }
                    is Resource.Error -> {
                        if (!result.data.isNullOrEmpty()) {
                            _isError.value = true
                        }
                        _isDataLoading.value = false
                    }
                    is Resource.Loading -> {
                        _isDataLoading.value = true
                        _isError.value = false
                    }
                }
            }
        }
    }

    fun onSearch(queryName: String) {
        viewModelScope.launch {
            filterEpisodesUseCase.filterEpisodesByName(queryName).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _filterEpisodes.postValue(result.data ?: emptyList())
                        _isDataLoading.postValue(false)
                        _isError.postValue(false)
                    }
                    is Resource.Error -> {
                        if (!result.data.isNullOrEmpty()) {
                            _isError.value = true
                        }
                        if (result.data == null) {
                            _noData.value = true
                        }
                        _isDataLoading.value = false
                    }
                    is Resource.Loading -> {
                        _isDataLoading.value = true
                        _isError.value = false
                    }

                }
            }
        }
    }
    fun loadMultipleEpisodes(id: List<Int>) {
        viewModelScope.launch {
            loadMultipleEpisodesUseCase.loadMultipleEpisodes(id).collectLatest { result ->
                when(result) {
                    is Resource.Success -> {
                        _filterEpisodes.postValue(result.data ?: emptyList())
                        _isDataLoading.postValue(false)
                        _isError.postValue(false)
                    }
                    is Resource.Error -> {
                        _filterEpisodes.postValue(result.data ?: emptyList())
                        _isError.value = true
                        _isDataLoading.value = false
                    }
                    is Resource.Loading -> {
                        _filterEpisodes.postValue(result.data ?: emptyList())
                        _isDataLoading.value = true
                        _isError.value = false
                    }
                }
            }
        }
    }
}