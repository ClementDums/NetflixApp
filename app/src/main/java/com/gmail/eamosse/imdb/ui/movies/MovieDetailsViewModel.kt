package com.gmail.eamosse.imdb.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.repository.MovieRepository

class MovieDetailsViewModel(private val repository: MovieRepository) : ViewModel(){
    private val _myNote: MutableLiveData<Float?> = MutableLiveData()
    val myNote: LiveData<Float?>
        get() = _myNote

    private val _movieDetails: MutableLiveData<Movie> = MutableLiveData()
    val movieDetails: LiveData<Movie>
        get() = _movieDetails

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    fun setMyNote(note:Float){
        _myNote.postValue(note*2)
    }
}