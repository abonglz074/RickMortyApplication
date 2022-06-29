package com.mytestprogram.rickmortyapplication.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mytestprogram.rickmortyapplication.domain.CharactersRepository
import com.mytestprogram.rickmortyapplication.domain.LoadAllCharactersUseCase
import com.mytestprogram.rickmortyapplication.domain.models.AllCharacters
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class ListCharactersViewModel @Inject constructor(
    private val loadAllCharactersUseCase: LoadAllCharactersUseCase
): ViewModel() {

    private val _allCharacters = MutableLiveData<AllCharacters>()
    val allCharacters: LiveData<AllCharacters> = _allCharacters

    private val _isDataLoading = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean> = _isDataLoading

    init {
        load()
    }

    private fun load() {
        _isDataLoading.value = true
        viewModelScope.launch {
            try {
                val loadedAllCharacters = loadAllCharactersUseCase.loadAllCharacters()
                _allCharacters.postValue(loadedAllCharacters)
                _isDataLoading.postValue(false)
            } catch (e:Exception) {
                _isDataLoading.postValue(false)
            }
        }
    }



}