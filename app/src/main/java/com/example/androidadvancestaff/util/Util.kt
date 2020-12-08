package com.example.androidadvancestaff.util

import android.content.Context
import android.graphics.Color
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.androidadvancestaff.R

fun getProgressDrawable(context:Context):CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth=7f
        centerRadius=50f
        backgroundColor=Color.WHITE
        start()
    }
}

fun ImageView.loadImage(path:String,loader:CircularProgressDrawable){
    val options= RequestOptions()
        .placeholder(loader)
        .error(R.mipmap.ic_launcher_round)

    val url = "https://image.tmdb.org/t/p/w500/$path"

    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}