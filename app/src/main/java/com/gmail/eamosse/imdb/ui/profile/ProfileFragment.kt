package com.gmail.eamosse.imdb.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.gmail.eamosse.imdb.databinding.FragmentProfileBinding
import com.gmail.eamosse.imdb.ui.trending.TrendingMoviesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private val profileViewModel: ProfileViewModel by viewModel()
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@ProfileFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(profileViewModel) {
            favoriteMovies.observe(viewLifecycleOwner, Observer {
                binding.favoriteMovies.trendingItems.adapter = TrendingMoviesAdapter(it)
            })

            likedMovies.observe(viewLifecycleOwner, Observer {
                binding.likedMovies.trendingItems.adapter = TrendingMoviesAdapter(it)
            })
        }
    }
}