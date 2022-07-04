package com.mytestprogram.rickmortyapplication.presentation.list_characters_screen

import androidx.lifecycle.*
import com.mytestprogram.rickmortyapplication.domain.models.characters.SingleCharacter
import com.mytestprogram.rickmortyapplication.domain.usecases.characters.LoadAllCharactersUseCase
import com.mytestprogram.rickmortyapplication.domain.usecases.characters.LoadSingleCharacterByName
import com.mytestprogram.rickmortyapplication.utils.Resource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListCharactersViewModel @Inject constructor(
    private val loadAllCharactersUseCase: LoadAllCharactersUseCase,
    private val loadSingleCharacterByName: LoadSingleCharacterByName
) : ViewModel() {

    private val _allCharacters = MutableLiveData<List<SingleCharacter>?>()
    val allCharacters: LiveData<List<SingleCharacter>?> = _allCharacters

    private val _isDataLoading = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean> = _isDataLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> = _isError

//    private val _searchQuery = mutableStateOf("")
//    val searchQuery: State<String> = _searchQuery
//
//    private val _state = mutableStateOf(ListCharactersState())
//    val state: State<ListCharactersState> = _state
//
//    private val _eventFlow = MutableSharedFlow<UIEvent>()
//    val eventFlow = _eventFlow.asSharedFlow()

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
}

//    private var searchJob: Job? = null

//    fun onSearch(query: String) {
//        _searchQuery.value = query
//        searchJob?.cancel()
//        searchJob = viewModelScope.launch {
//            delay(500L)
//            loadSingleCharacterByName.loadCharacterByName(query)
//                .onEach { result ->
//                    when (result) {
//                        is Resource.Success -> {
//                            _state.value = state.value.copy(
//                                allCharacters = result.data ?: emptyList(),
//                                isLoading = false
//                            )
//                        }
//                        is Resource.Error -> {
//                            _state.value = state.value.copy(
//                                allCharacters = result.data ?: emptyList(),
//                                isLoading = false
//                            )
//                            _eventFlow.emit(
//                                UIEvent.ShowToast(
//                                    "Unknown error"
//                                )
//                            )
//                        }
//                        is Resource.Loading -> {
//                            _state.value = state.value.copy(
//                                allCharacters = result.data ?: emptyList(),
//                                isLoading = true
//                            )
//                        }
//
//                    }
//                }.launchIn(this)
//        }
//    }

