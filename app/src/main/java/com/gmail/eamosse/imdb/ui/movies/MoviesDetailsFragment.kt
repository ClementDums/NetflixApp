package com.gmail.eamosse.imdb.ui.movies

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gmail.eamosse.idbdata.data.Movie
import com.gmail.eamosse.idbdata.data.Video
import com.gmail.eamosse.imdb.BR
import com.gmail.eamosse.imdb.databinding.FragmentMoviesDetailBinding
import com.gmail.eamosse.imdb.ui.home.CategoryAdapter
import com.gmail.eamosse.imdb.ui.home.HomeFragmentDirections
import com.gmail.eamosse.imdb.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesDetailsFragment : Fragment() {
    private val viewModel: MovieDetailsViewModel by viewModel()
    private lateinit var binding: FragmentMoviesDetailBinding
    val args: MoviesDetailsFragmentArgs by navArgs()
    lateinit var movie: Movie

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

        with(viewModel) {
            getMovieDetails(args.movieId)
            getMovieVideos(args.movieId)
            movieDetails.observe(viewLifecycleOwner, Observer {
                binding.item = it
                binding.trailerVideo.webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                        view?.loadUrl(url)
                        return true
                    }
                }
                binding.trailerVideo.settings.javaScriptEnabled = true
                val video = it.videos ?: emptyList()
                if (video.isNotEmpty()) {
                    val url = video[0].key
                    binding.trailerVideo.loadData(
                        "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/${url}\" frameborder=\"0\" allowfullscreen></iframe>",
                        "text/html",
                        "utf-8"
                    )
                }

                binding.companiesRecycler.adapter = it.production_companies?.let { it1 ->
                    ProductionCompaniesAdapter(
                        it1
                    )
                }
            })

            error.observe(viewLifecycleOwner, Observer {
                //afficher l'erreur
            })
        }

    }
}

