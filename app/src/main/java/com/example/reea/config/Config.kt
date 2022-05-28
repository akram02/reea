package com.example.reea.config

object Config {
    val REQUEST_TIMEOUT = 60L
    val BASE_URL = "https://api.themoviedb.org/3/"
    val POSTER_PATH = "https://image.tmdb.org/t/p/w342"
    const val API = "1a97f3b8d5deee1d649c0025f3acf75c"
    const val GET_MOVIES = "discover/movie?api_key=$API&primary_release_year=2020&sort_by=vote_average.desc"
    const val GET_MOVIE_DETAILS = "movie/{id}?api_key=$API"
}