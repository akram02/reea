package com.example.reea.ui

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.reea.network.RestApi
import com.example.reea.vm.MovieVM
import javax.inject.Inject

class MovieSource @Inject constructor(private val api: RestApi) : PagingSource<Int, MovieVM>() {
    override fun getRefreshKey(state: PagingState<Int, MovieVM>): Int? {
        val anchorPosition = state.anchorPosition ?: return null

        return state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
            ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieVM> {
        val position = params.key ?: 1

        var prevKey: Int? = null
        if (position!=1)
            prevKey = position-1

        val nextKey = position + 1

        return try {
            val response = api.getMovieList(position)
            LoadResult.Page(
                data = response.results!!,
                prevKey = prevKey,
                nextKey = nextKey
            )
        }
        catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}