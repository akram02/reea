package com.example.reea.network

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.reea.Config.INITIAL_LOAD_SIZE
import com.example.reea.Config.NETWORK_PAGE_SIZE
import com.example.reea.base.BaseViewModel
import com.example.reea.ui.MovieSource
import com.example.reea.vm.MovieDetailsVM
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(
    private val api: RestApi,
    private val movieSource: MovieSource
) : BaseViewModel() {

    val movieDetails = MutableLiveData<MovieDetailsVM>()

    val items = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false,
            initialLoadSize = INITIAL_LOAD_SIZE
        ),
        pagingSourceFactory = {
            movieSource
        },
        initialKey = 1
    ).liveData.cachedIn(viewModelScope)


    fun getMovieDetails(id: Int) {
        getResponse(api::getMovieDetails, id) {
            movieDetails.value = it
        }
    }
}