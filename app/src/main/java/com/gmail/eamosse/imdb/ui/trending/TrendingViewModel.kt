package com.gmail.eamosse.imdb.ui.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.data.Person
import com.gmail.eamosse.idbdata.repository.MovieRepository
import com.gmail.eamosse.idbdata.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TrendingViewModel(private val repository: MovieRepository) : ViewModel() {


    private val _trendingMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    val trendingMovies: LiveData<List<Movie>>
        get() = _trendingMovies

    private val _categories: MutableLiveData<List<Category>> = MutableLiveData()
    val categories: LiveData<List<Category>>
        get() = _categories

    private val _trendingPeople: MutableLiveData<List<Person>> = MutableLiveData()
    val trendingPeople: LiveData<List<Person>>
        get() = _trendingPeople

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    init {
        loadTrendingPeople()
        loadTrendingMovies()
    }


  fun loadTrendingMovies(){
      viewModelScope.launch(Dispatchers.IO) {
          when (val result = repository.getTrendingMovies()) {
              is Result.Succes -> {
                  println("EEE ${result.data}")
                  _trendingMovies.postValue(result.data)
              }
              is Result.Error -> {
                  _error.postValue(result.message)
              }
          }
      }
  }
    fun loadTrendingPeople(){
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getTrendingPeople()) {
                is Result.Succes -> {
                    _trendingPeople.postValue(result.data)
                    println("EEE ${result.data}")
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                    println("EEE ${result.message}")

                }
            }
        }
    }


    fun loadTrendingCategories(){
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getCategories()) {
                is Result.Succes -> {
                    _categories.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }
}