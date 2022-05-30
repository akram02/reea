package com.example.reea.ui

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.reea.Config
import com.example.reea.R
import com.example.reea.base.BasePagingAdapter
import com.example.reea.vm.MovieVM
import javax.inject.Inject

class MovieAdapter @Inject
constructor() : BasePagingAdapter<MovieVM>(R.layout.item_movie, ARTICLE_DIFF_CALLBACK) {
    @SuppressLint("SetTextI18n")
    override fun bindView(view: View, any: MovieVM, position: Int) {
        with(view) {
            val title = view.findViewById<TextView>(R.id.title)
            val image = view.findViewById<ImageView>(R.id.image)
            val date = view.findViewById<TextView>(R.id.date)
            val count = view.findViewById<TextView>(R.id.count)
            title.text = "Title: " + any.title
            if (any.posterPath != null) {
                Glide.with(view).load(Config.POSTER_PATH + any.posterPath).into(image)
            }
            date.text = "Date: " + any.releaseDate
            count.text = "Vote: " + any.voteCount.toString()
            setOnClickListener { clickListener.onItemClick(view, any) }
        }
    }
}

val ARTICLE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieVM>() {
    override fun areItemsTheSame(oldItem: MovieVM, newItem: MovieVM) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MovieVM, newItem: MovieVM) =
        oldItem.equals(newItem)
}
