package com.gmail.eamosse.imdb.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.data.Video
import com.gmail.eamosse.idbdata.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.gmail.eamosse.idbdata.utils.Result


class MovieDetailsViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _movieDetails: MutableLiveData<Movie> = MutableLiveData()
    val movieDetails: LiveData<Movie>
        get() = _movieDetails

    private val _movieVideos: MutableLiveData<List<Video>> = MutableLiveData()
    val movieVideos: LiveData<List<Video>>
        get() = _movieVideos

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error


    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getMovieDetails(movieId)) {
                is Result.Success -> {
                    _movieDetails.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getMovieVideos(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getMovieVideos(movieId)) {
                is Result.Success -> {
                    _movieVideos.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun setLike(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            val like = repository.isLiked(movie.id) != 1
            repository.registerMovie(movie = movie)
            repository.setLike(movie_id = movie.id, isLiked = like)
        }
    }

    fun setFavorite(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            val favorite = repository.isFavorite(movie.id) != 1
            repository.registerMovie(movie = movie)
            repository.setFavorite(movie_id = movie.id, isFavorite = favorite)
        }
    }
}