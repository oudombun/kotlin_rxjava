package com.example.androidadvancestaff.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidadvancestaff.R
import com.example.androidadvancestaff.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MovieViewModel
    private val movieAdapter = ListMovieAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        observeViewModel()
    }

    private fun init() {
        //viewmodel
        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        viewModel.refresh()

        //swipe to refresh
        swipelayout.setOnRefreshListener {
            swipelayout.isRefreshing= false
            viewModel.refresh()
        }

        //recyclerview
        recyclerView.apply {
            layoutManager= LinearLayoutManager(context)
            adapter =movieAdapter
        }
    }

    private fun observeViewModel(){
        viewModel.movies.observe(this, Observer { list->
            list?.let {
                recyclerView.visibility= View.VISIBLE
                list.result?.let { result -> movieAdapter.updateMovie(result) }
            }
        })

        viewModel.loadError.observe(this, Observer { isError->
            isError?.let { error_text.visibility = if(it) View.VISIBLE else View.GONE
        } })

        viewModel.loading.observe(this, Observer { isLoading->
            run {
                isLoading?.let {
                    progressbar.visibility = if (it) View.VISIBLE else View.GONE
                    if (it) {
                        recyclerView.visibility = View.GONE
                        error_text.visibility = View.GONE
                    }
                }
            }
        })
    }
}
