package com.mytestprogram.rickmortyapplication.presentation.list_characters_screen

import androidx.lifecycle.*
import com.mytestprogram.rickmortyapplication.domain.models.characters.SingleCharacter
import com.mytestprogram.rickmortyapplication.domain.usecases.characters.FilterCharactersUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.characters.LoadAllCharactersUseCase
import com.mytestprogram.rickmortyapplication.utils.Resource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListCharactersViewModel @Inject constructor(
    private val loadAllCharactersUseCase: LoadAllCharactersUseCase,
    private val filterCharactersUseCase: FilterCharactersUseCase
) : ViewModel() {

    private val _allCharacters = MutableLiveData<List<SingleCharacter>?>()
    val allCharacters: LiveData<List<SingleCharacter>?> = _allCharacters

    private val _filterCharacter = MutableLiveData<List<SingleCharacter>>()
    val filterCharacter: LiveData<List<SingleCharacter>> = _filterCharacter

    private val _isDataLoading = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean> = _isDataLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

    fun loadAllCharacters() {
        viewModelScope.launch {
            loadAllCharactersUseCase.loadAllCharacters().collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _allCharacters.postValue(result.data)
                        _isDataLoading.value = false
                        _isError.postValue(false)
                    }
                    is Resource.Error -> {
                        _allCharacters.postValue(result.data)
                        if (result.data.isNullOrEmpty()) {
                            _isError.postValue(true)
                        }
                        _isDataLoading.postValue(false)
                    }
                    is Resource.Loading -> {
                        if (result.data.isNullOrEmpty()) {
                            _isDataLoading.value = false
                        }
                        _isError.postValue(false)
                    }
                }
            }
        }
    }

    fun onSearch(queryName: String) {
        viewModelScope.launch {
            filterCharactersUseCase.filterCharacterByName(queryName).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _filterCharacter.postValue(result.data ?: emptyList())
                        _isDataLoading.postValue(false)
                        _isError.postValue(false)
                    }

                }
            }
        }
    }

    fun filterByStatus(status: String) {
        viewModelScope.launch {
            filterCharactersUseCase.filterCharacterByStatus(status).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _filterCharacter.postValue(result.data ?: emptyList())
                        _isDataLoading.postValue(false)
                        _isError.postValue(false)
                    }

                }
            }
        }
    }

    fun filterByGender(gender: String) {
        viewModelScope.launch {
            filterCharactersUseCase.filterCharacterByGender(gender).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _filterCharacter.postValue(result.data ?: emptyList())
                        _isDataLoading.postValue(false)
                        _isError.postValue(false)
                    }

                }
            }
        }
    }

    fun filterBySpecies(species: String) {
        viewModelScope.launch {
            filterCharactersUseCase.filterCharacterBySpecies(species).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _filterCharacter.postValue(result.data ?: emptyList())
                        _isDataLoading.postValue(false)
                        _isError.postValue(false)
                    }

                }
            }
        }
    }
}


