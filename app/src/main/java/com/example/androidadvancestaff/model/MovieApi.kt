package com.example.androidadvancestaff.model

import io.reactivex.Single
import retrofit2.http.GET

interface MovieApi {
    @GET("/3/movie/popular?api_key=53a62b5cbdff27ac8ad1dcf83dfaaf21")
    fun getMovies():Single<MovieResponse>
}