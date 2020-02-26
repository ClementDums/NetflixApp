package com.gmail.eamosse.imdb.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.imdb.databinding.FragmentMoviesDetailBinding
import com.gmail.eamosse.imdb.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_movies_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesDetailsFragment: Fragment(){
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding:FragmentMoviesDetailBinding
    val args:MoviesDetailsFragmentArgs by navArgs()
    lateinit var movie:Movie


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movie = args.movie
        movie_title.text = movie.title
        movie_overview.text = movie.overview
    }
}

