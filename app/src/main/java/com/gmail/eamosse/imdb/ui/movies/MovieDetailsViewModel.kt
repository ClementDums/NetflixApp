package com.gmail.eamosse.imdb.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.repository.MovieRepository

class MovieDetailsViewModel(private val repository: MovieRepository) : ViewModel(){
    private val _movieDetails: MutableLiveData<Movie> = MutableLiveData()
    val movieDetails: LiveData<Movie>
        get() = _movieDetails

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

//    fun getMovieDetail(movieId:Int){
//        viewModelScope.launch(Dispatchers.IO) {
//            when (val result = repository.getMovieDetail(movieId)) {
//                is Result.Succes -> {
//                    _movieDetail.postValue(result.data)
//                }
//                is Result.Error -> {
//                    _error.postValue(result.message)
//                }
//            }
//        }
//    }
}