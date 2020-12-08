package com.example.androidadvancestaff.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidadvancestaff.R
import com.example.androidadvancestaff.model.Movie
import com.example.androidadvancestaff.util.getProgressDrawable
import com.example.androidadvancestaff.util.loadImage
import kotlinx.android.synthetic.main.item_movie.view.*

class ListMovieAdapter(var movies:ArrayList<Movie>):RecyclerView.Adapter<ListMovieAdapter.ListCountryMovie>()  {

    fun updateMovie(newList:List<Movie>){
        movies.clear()
        movies.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ListCountryMovie(
        LayoutInflater.from(parent.context).inflate(R.layout.item_movie,parent,false)
    )

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: ListCountryMovie, position: Int) {
        holder.bind(movies[position])
    }

    class ListCountryMovie(view:View):RecyclerView.ViewHolder(view){
        private val movieName=view.name
        private val movieOverview=view.txt_des
        private val imageView=view.img
        private val progressDrawable =getProgressDrawable(view.context)
        fun bind(movie:Movie){
            movieName.text = movie.MovieName
            movieOverview.text = movie.MovieDes
            movie.MovieImg?.let { imageView.loadImage(it,progressDrawable) }
        }
    }


}