package com.gmail.eamosse.imdb.ui.actors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.gmail.eamosse.imdb.databinding.FragmentActorsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ActorsListFragment : Fragment() {
    private val actorsViewModel: ActorsViewModel by viewModel()
    val args: ActorsDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentActorsBinding
    private lateinit var layoutManager: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActorsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { // TODO
        super.onViewCreated(view, savedInstanceState)
        layoutManager = GridLayoutManager(activity, 4)
        with(actorsViewModel) {
        }
    }
}