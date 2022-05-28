package com.example.reea.vm

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieListVM {
    @SerializedName("page")
    @Expose
    var page: Int? = null

    @SerializedName("results")
    @Expose
    var results: List<MovieVM>? = null

    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null

    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null
}