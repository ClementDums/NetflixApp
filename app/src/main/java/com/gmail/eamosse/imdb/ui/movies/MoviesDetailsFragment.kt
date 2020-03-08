package com.gmail.eamosse.imdb.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.imdb.BR
import com.gmail.eamosse.imdb.databinding.FragmentMoviesDetailBinding
import com.gmail.eamosse.imdb.ui.home.CategoryAdapter
import com.gmail.eamosse.imdb.ui.home.HomeFragmentDirections
import com.gmail.eamosse.imdb.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_movies_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesDetailsFragment: Fragment(){
    private val viewModel: MovieDetailsViewModel by viewModel()
    private lateinit var binding:FragmentMoviesDetailBinding
    val args:MoviesDetailsFragmentArgs by navArgs()
    lateinit var movie:Movie

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesDetailBinding.inflate(inflater, container, false)
        binding.setVariable(BR.item, args.movie)
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            movieDetails.observe(viewLifecycleOwner, Observer {
                binding.item = it
            })

            myNote.observe(viewLifecycleOwner, Observer {

            })

            error.observe(viewLifecycleOwner, Observer {
                //afficher l'erreur
            })
        }

        rating_bar.setOnRatingBarChangeListener { ratingBar, fl, b ->
            viewModel.setMyNote(fl)
        }
    }
}

