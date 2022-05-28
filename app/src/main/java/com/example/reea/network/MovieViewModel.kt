package com.example.reea.network

import androidx.lifecycle.MutableLiveData
import com.example.reea.base.BaseViewModel
import com.example.reea.network.RestApi
import com.example.reea.vm.MovieListVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val api: RestApi): BaseViewModel() {

    val movieListLiveData = MutableLiveData<MovieListVM>()

    fun getMovieList() {
        getResponse(api::getMovieList) {
            movieListLiveData.value = it
        }
    }

    fun getMovieDetails(id: Int) {
        getResponse(api::getMovieDetails, id) {

        }
    }
}