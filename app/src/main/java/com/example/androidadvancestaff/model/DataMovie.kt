package com.example.androidadvancestaff.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("original_title")
    val MovieName:String?,
    @SerializedName("overview")
    val MovieDes:String?,
    @SerializedName("poster_path")
    val MovieImg:String?

)

data class MovieResponse(
    @SerializedName("results")
    val result:List<Movie>?
)