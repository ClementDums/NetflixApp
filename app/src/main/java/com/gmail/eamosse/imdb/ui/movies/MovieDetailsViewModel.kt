package com.gmail.eamosse.imdb.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.data.Token
import com.gmail.eamosse.idbdata.repository.MovieRepository
import com.gmail.eamosse.idbdata.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.net.Uri


class MovieDetailsViewModel(private val repository: MovieRepository) : ViewModel(){
    private val _token: MutableLiveData<Token> = MutableLiveData()
    val token: LiveData<Token>
        get() = _token

    private val _myNote: MutableLiveData<Float?> = MutableLiveData()
    val myNote: LiveData<Float?>
        get() = _myNote

    private val _movieDetails: MutableLiveData<Movie> = MutableLiveData()
    val movieDetails: LiveData<Movie>
        get() = _movieDetails

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    init {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getToken()) {
                is Result.Succes -> {
                    _token.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun getSession(){
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getSession(token.value!!.requestToken)) {
                is Result.Succes -> {
                   println("EEEE ${result.data.sessionId}")
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    private fun rateMovie(note: Float, id:Int?){
        viewModelScope.launch(Dispatchers.IO) {
           repository.rateMovie(note,id)
        }
    }

    fun getMovieDetails(movie:Movie){
        _movieDetails.postValue(movie)
    }
    fun setMyNote(note:Float){
        val realNote = note*2
        _myNote.postValue(realNote)
        rateMovie(realNote, movieDetails.value?.id)
    }
}