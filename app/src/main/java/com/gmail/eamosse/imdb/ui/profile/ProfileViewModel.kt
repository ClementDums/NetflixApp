package com.gmail.eamosse.imdb.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: MovieRepository) : ViewModel(){
    private val _likedMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    val likedMovies: LiveData<List<Movie>>
        get() = _likedMovies

    private val _favoriteMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    val favoriteMovies: LiveData<List<Movie>>
        get() = _favoriteMovies

    init {
        loadLikedMovies()
        loadFavoriteMovies()
    }

    private fun loadLikedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getLiked()
            _likedMovies.postValue(result)
        }
    }

    private fun loadFavoriteMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getFavorites()
            _favoriteMovies.postValue(result)
        }
    }
}