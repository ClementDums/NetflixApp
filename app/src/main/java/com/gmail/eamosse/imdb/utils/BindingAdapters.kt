package com.gmail.eamosse.imdb.utils

import android.graphics.Color
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.gmail.eamosse.imdb.R

@BindingAdapter("app:bindImage")
fun bindImage(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView)
            .load("https://image.tmdb.org/t/p/original/$url")
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
            .centerCrop()
            .into(imageView)
    }
}