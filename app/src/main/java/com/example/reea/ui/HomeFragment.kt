package com.example.reea.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reea.R
import com.example.reea.base.ClickListener
import com.example.reea.databinding.FragmentHomeBinding
import com.example.reea.network.MovieViewModel
import com.example.reea.network.NetworkError
import com.example.reea.utils.LanguageUtils
import com.example.reea.vm.MovieVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
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
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.app_name)
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
        val movieAdapter = MovieAdapter()
        binding.movieList.adapter = movieAdapter
        binding.movieList.layoutManager = LinearLayoutManager(requireContext())
        movieAdapter.setItemClickListener(this)
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                movieAdapter.loadStateFlow.collect {
                    binding.prependProgress.isVisible = it.source.prepend is LoadState.Loading
                    binding.appendProgress.isVisible = it.source.append is LoadState.Loading
                }
            }

        }
        movieAdapter.addLoadStateListener {
            val error = NetworkError.getErrorMessage(it)
            if (error!=null) {
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.items.observe(viewLifecycleOwner) { data->
            lifecycleScope.launch {
                movieAdapter.submitData(data)
            }
        }

        return binding.root
    }

    override fun onItemClick(item: View, any: Any) {
        findNavController().navigate(
            HomeFragmentDirections.homeFragmentToMovieDetails(any as MovieVM)
        )
    }
}