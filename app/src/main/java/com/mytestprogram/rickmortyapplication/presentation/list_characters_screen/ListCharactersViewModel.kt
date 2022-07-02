package com.mytestprogram.rickmortyapplication.presentation.list_characters_screen

import androidx.lifecycle.*
import com.bumptech.glide.load.engine.Resource
import com.mytestprogram.rickmortyapplication.domain.usecases.LoadAllCharactersUseCase
import com.mytestprogram.rickmortyapplication.domain.models.characters.AllCharacters
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class ListCharactersViewModel @Inject constructor(
    private val loadAllCharactersUseCase: LoadAllCharactersUseCase
): ViewModel() {

    val allCharacters = loadAllCharactersUseCase.loadAllCharacters().asLiveData()

//    private val _allCharacters = MutableLiveData<AllCharacters>()
//    val allCharacters: LiveData<AllCharacters> = _allCharacters
//
//    private val _isDataLoading = MutableLiveData<Boolean>()
//    val isDataLoading: LiveData<Boolean> = _isDataLoading
//
//    private val _isError = MutableLiveData<Boolean>()
//    val isError: LiveData<Boolean> = _isError
//
//    init {
//        load()
//    }
//
//    private fun load() {
//        _isDataLoading.value = true
//        _isError.value = false
//        viewModelScope.launch {
//            try {
//                val loadedAllCharacters = loadAllCharactersUseCase.loadAllCharacters()
//                _allCharacters.postValue(loadedAllCharacters)
//                _isDataLoading.value = false
//                _isError.value = false
//            } catch (e:Exception) {
//                _isDataLoading.value = false
//                _isError.value = true
//            }
//        }
//    }




}