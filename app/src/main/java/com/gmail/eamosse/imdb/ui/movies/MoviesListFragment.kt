package com.gmail.eamosse.imdb.ui.movies

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.imdb.databinding.FragmentMoviesListBinding
import com.gmail.eamosse.imdb.ui.home.DiscoversAdapter
import com.gmail.eamosse.imdb.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_movies_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MoviesListFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    val args: MoviesListFragmentArgs by navArgs()
    private lateinit var binding: FragmentMoviesListBinding
    private lateinit var layoutManager: GridLayoutManager

    private var pastVisibleItems = 0
    private var visibleItemCounts = 0
    private var totalItemCount = 0
    private var previousTotal = 0
    private var page = 1
    private var isLoading = true
    private var recyclerViewState: Parcelable? = null


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
        layoutManager = GridLayoutManager(activity, 1)
        binding.moviesList.setHasFixedSize(true)
        binding.moviesList.layoutManager = layoutManager
        with(homeViewModel) {

            token.observe(viewLifecycleOwner, Observer {
                //récupérer les catégories
                getDiscover(args.category)
            })

            discovers.observe(viewLifecycleOwner, Observer {
                if (page > 1) {
                    binding.moviesList.scrollToPosition(it.count() - 21)
                } else {
                    binding.moviesList.scrollToPosition(it.count() - 20)

                }
                binding.moviesList.adapter = DiscoversAdapter(it) {
                    val action =
                        MoviesListFragmentDirections.actionNavigationMoviesListFragmentToNavigationMoviesDetailFragment(
                            it.id
                        )
                    findNavController().navigate(action)
                }
            })

            showProgressBar.observe(viewLifecycleOwner, Observer {
                progress_bar.isVisible = it
            })

            showShimmer.observe(viewLifecycleOwner, Observer {
                if (!it) {
                    binding.shimmerViewContainer.hideShimmer()
                }
            })

            error.observe(viewLifecycleOwner, Observer {
                //afficher l'erreur
            })
        }
        binding.moviesList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                visibleItemCounts = layoutManager.childCount
                totalItemCount = layoutManager.itemCount
                pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

                if (dy > 0) {
                    if (isLoading) {
                        if (totalItemCount > previousTotal) {
                            isLoading = false
                            previousTotal = totalItemCount
                        }
                    }
                    if (!isLoading && (totalItemCount - visibleItemCounts) <= pastVisibleItems) {
                        page++
                        homeViewModel.pageNumber = page
                        homeViewModel.getDiscover(args.category)
                        isLoading = true
                    }
                }
            }
        })
    }

}