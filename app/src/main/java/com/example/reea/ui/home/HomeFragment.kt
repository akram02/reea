package com.example.reea.ui.home

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
    lateinit var preference: LanguageUtils

    @Inject
    lateinit var application: Application

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
            preference.toggleLanguage(requireContext())
            requireActivity().recreate()
        }
        return binding.root
    }
}