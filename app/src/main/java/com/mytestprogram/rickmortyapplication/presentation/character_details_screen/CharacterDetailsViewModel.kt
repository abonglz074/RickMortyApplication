package com.mytestprogram.rickmortyapplication.presentation.character_details_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mytestprogram.rickmortyapplication.domain.models.characters.SingleCharacter
import com.mytestprogram.rickmortyapplication.domain.usecases.LoadSingleCharacterByIdUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailsViewModel @Inject constructor(
     val loadSingleCharacterByIdUseCase: LoadSingleCharacterByIdUseCase
): ViewModel() {

    private val _singleCharacter = MutableLiveData<SingleCharacter>()
    val singleCharacter: LiveData<SingleCharacter> = _singleCharacter

    private val _isDataLoading = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean> = _isDataLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError


     fun loadCharacterById(characterId: Int) {
        _isDataLoading.value = true
        _isError.value = false
        viewModelScope.launch {
            try {
                val loadedSingleCharacter = loadSingleCharacterByIdUseCase.loadCharacterById(characterId)
                _singleCharacter.postValue(loadedSingleCharacter)
                _isDataLoading.value = false
                _isError.value = false
            } catch (e: Exception) {
                _isDataLoading.value = false
                _isError.value = true

            }
        }
    }


}