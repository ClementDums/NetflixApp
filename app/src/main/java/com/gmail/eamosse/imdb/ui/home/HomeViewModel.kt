package com.gmail.eamosse.imdb.ui.home

import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.data.Token
import com.gmail.eamosse.idbdata.repository.MovieRepository
import com.gmail.eamosse.idbdata.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.*

class HomeViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _token: MutableLiveData<Token> = MutableLiveData()
    val token: LiveData<Token>
        get() = _token

    private val _categories: MutableLiveData<List<Category>> = MutableLiveData()
    val categories: LiveData<List<Category>>
        get() = _categories

    private val _discovers: MutableLiveData<List<Movie>> by lazy {
        MutableLiveData<List<Movie>>()
    }
    val discovers: LiveData<List<Movie>>
        get() = _discovers

    private val _mutableShowShimmer = MutableLiveData<Boolean>()
    val showShimmer: MutableLiveData<Boolean> = _mutableShowShimmer

    private val _showProgressBar = MutableLiveData<Boolean>()
    val showProgressBar: MutableLiveData<Boolean> = _showProgressBar

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    var pageNumber = 1
    private var discoversArray = emptyList<Movie>()

    init {
        _showProgressBar.value = true
        _mutableShowShimmer.value = true
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getToken()) {
                is Result.Success -> {
                    _token.postValue(result.data)
                    stopShimmer()
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getCategories()) {
                is Result.Success -> {
                    _categories.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getDiscover(genre_id: Int) {
        _showProgressBar.value = true
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getDiscover(genre_id, pageNumber)) {
                is Result.Success -> {
                    discoversArray = discoversArray + result.data
                    _discovers.postValue(discoversArray)

                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
            stopShimmer()
            stopProgressBar()
        }
    }

    fun stopProgressBar() = viewModelScope.launch {
        _showProgressBar.value = false
    }

    fun stopShimmer() = viewModelScope.launch {
        _mutableShowShimmer.value = false
    }

}