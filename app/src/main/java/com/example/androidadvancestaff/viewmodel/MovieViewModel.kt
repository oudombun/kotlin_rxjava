package com.example.androidadvancestaff.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidadvancestaff.model.Movie
import com.example.androidadvancestaff.model.MovieResponse
import com.example.androidadvancestaff.model.MovieService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlin.math.log

class MovieViewModel:ViewModel() {
    val movies = MutableLiveData<MovieResponse>()
    val loadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    private val movieService= MovieService()
    private val disposable  = CompositeDisposable()

    fun refresh(){
        fetchCountry()
    }

    private fun fetchCountry(){
        loading.value = true
        disposable.add(
            movieService.getMovies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object:DisposableSingleObserver<MovieResponse>(){
                    override fun onSuccess(value: MovieResponse?) {
                        movies.value = value
                        loadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable?) {
                        loadError.value = true
                        loading.value = false
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}