package com.example.reea.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.reea.databinding.FragmentMovieDetailsBinding
import com.example.reea.network.MovieViewModel
import com.example.reea.vm.MovieVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailsBinding

    private val viewModel by viewModels<MovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        val movie: MovieVM = MovieDetailsFragmentArgs.fromBundle(requireArguments()).movie
        viewModel.getMovieDetails(movie.id ?: 0)
        return binding.root
    }
}