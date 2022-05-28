package com.example.reea.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reea.base.ClickListener
import com.example.reea.databinding.FragmentHomeBinding
import com.example.reea.utils.LanguageUtils
import com.example.reea.vm.MovieVM
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * App starts with this fragment.
 * Contains movie list with two buttons(go to map, change language)
 */
@AndroidEntryPoint
class HomeFragment : Fragment(), ClickListener {
    @Inject
    lateinit var languageUtils: LanguageUtils

    private val viewModel by viewModels<MovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.mapsBtn.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.homeFragmentToMapsFragment()
            )
        }

        binding.languageBtn.setOnClickListener {
            languageUtils.toggleLanguage(requireContext())
            requireActivity().recreate()
        }
        viewModel.getMovieList()
        val movieAdapter = MovieAdapter()
        binding.movieList.adapter = movieAdapter
        binding.movieList.layoutManager = LinearLayoutManager(requireContext())
        movieAdapter.setItemClickListener(this)
        viewModel.movieListLiveData.observe(viewLifecycleOwner) {
            movieAdapter.setItemList(it.results!!.toMutableList())
        }
        return binding.root
    }

    override fun onItemClick(item: View, any: Any) {
        findNavController().navigate(
            HomeFragmentDirections.homeFragmentToMovieDetails(any as MovieVM)
        )
    }
}