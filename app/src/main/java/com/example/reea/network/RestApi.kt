package com.example.reea.network

import com.example.reea.Config
import com.example.reea.vm.MovieDetailsVM
import com.example.reea.vm.MovieListVM
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApi {
    @GET(Config.GET_MOVIES)
    suspend fun getMovieList(@Query("page") page: Int): MovieListVM

    @GET(Config.GET_MOVIE_DETAILS)
    suspend fun getMovieDetails(@Path("id") id: Int): MovieDetailsVM
}