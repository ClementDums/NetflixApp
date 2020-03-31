package com.gmail.eamosse.imdb.ui.actors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.eamosse.idbdata.data.Actor
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.repository.MovieRepository

class ActorsViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _actors: MutableLiveData<Actor> = MutableLiveData()
    val actors: LiveData<Actor>
        get() = _actors

    private val _movies: MutableLiveData<Movie> = MutableLiveData()
    val movies: LiveData<Movie>
        get() = _movies

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    fun getActors(filters: String) {

    }

    fun getActorsDetails(actorId: Int) {

    }

    fun getMoviesByActor(actor: Actor) {

    }
}