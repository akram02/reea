package com.example.reea.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.reea.databinding.FragmentMovieDetailsBinding
import com.example.reea.vm.MovieVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    lateinit var binding: FragmentMovieDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        val movie: MovieVM = MovieDetailsFragmentArgs.fromBundle(requireArguments()).movie
        binding.title.text = movie.title
        return binding.root
    }
}