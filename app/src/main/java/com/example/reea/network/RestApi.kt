package com.example.reea.network

import com.example.reea.config.Config
import com.example.reea.vm.MovieDetailsVM
import com.example.reea.vm.MovieListVM
import retrofit2.http.GET
import retrofit2.http.Path

interface RestApi {
    @GET(Config.GET_MOVIES)
    suspend fun getMovieList(): MovieListVM

    @GET(Config.GET_MOVIE_DETAILS)
    suspend fun getMovieDetails(@Path("id") id: Int): MovieDetailsVM
}