package com.example.reea.ui.home

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.reea.R
import com.example.reea.base.BaseAdapter
import com.example.reea.config.Config
import com.example.reea.vm.MovieVM

import javax.inject.Inject

class MovieAdapter @Inject
constructor() : BaseAdapter(R.layout.item_movie) {
    @SuppressLint("SetTextI18n")
    override fun bindView(view: View, any: Any, position: Int) {
        with(view) {
            val movie = any as MovieVM
            val title = view.findViewById<TextView>(R.id.title)
            val image = view.findViewById<ImageView>(R.id.image)
            val date = view.findViewById<TextView>(R.id.date)
            val count = view.findViewById<TextView>(R.id.count)
            title.text = "Title: " + movie.title
            Glide.with(view).load(Config.POSTER_PATH + movie.posterPath).into(image)
            date.text = "Date: " + movie.releaseDate
            count.text = "Vote: " + movie.voteCount.toString()
            setOnClickListener { clickListener.onItemClick(view, movie) }
        }
    }
}