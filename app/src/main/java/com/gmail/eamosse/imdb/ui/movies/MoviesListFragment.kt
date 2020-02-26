package com.gmail.eamosse.imdb.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gmail.eamosse.imdb.databinding.FragmentMoviesListBinding
import com.gmail.eamosse.imdb.ui.home.CategoryAdapter
import com.gmail.eamosse.imdb.ui.home.DiscoversAdapter
import com.gmail.eamosse.imdb.ui.home.HomeFragmentDirections
import com.gmail.eamosse.imdb.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesListFragment:Fragment(){

    private val homeViewModel: HomeViewModel by viewModel()
    val args:MoviesListFragmentArgs by navArgs()
    private lateinit var binding: FragmentMoviesListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(homeViewModel) {
            token.observe(viewLifecycleOwner, Observer {
                //récupérer les catégories
                getMovie(args.category)
            })

            discovers.observe(viewLifecycleOwner, Observer {
                binding.moviesList.adapter = DiscoversAdapter(it){
                    val action = MoviesListFragmentDirections.actionNavigationMoviesListFragmentToNavigationMoviesDetailFragment(it)
                   findNavController().navigate(action)
                }
            })


            error.observe(viewLifecycleOwner, Observer {
                //afficher l'erreur
            })
        }
    }
}