package com.example.reea.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.reea.databinding.FragmentHomeBinding
import com.example.reea.utils.LanguageUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * App starts with this fragment
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {
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
        viewModel.movieListLiveData.observe(viewLifecycleOwner) {
            println(it)
        }
        return binding.root
    }
}