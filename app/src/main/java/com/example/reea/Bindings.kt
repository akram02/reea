package com.example.reea

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("glideSrc")
fun ImageView.bindGlideSrc(imageUrl: String?) {
    if (imageUrl == null) return
    val url = Config.POSTER_PATH + imageUrl
    Glide.with(context).load(url).into(this)
}
