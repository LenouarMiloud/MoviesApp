package com.fsociety.moviesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fsociety.moviesapp.R
import com.fsociety.moviesapp.models.Movie
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter (
    private val movies : List<Movie>

):RecyclerView.Adapter<MovieAdapter.MovieHolderView>(){

    private lateinit var mListener: OnItemClickListener
    companion object {
        const val IMG_BASE = "https://image.tmdb.org/t/p/w500/"
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener = listener
    }


    class MovieHolderView(view: View,listener: OnItemClickListener): RecyclerView.ViewHolder(view){

        fun bindMovie(movie:Movie){
            itemView.movieTitle.text = movie.title
            itemView.movieDate.text = movie.release_date
            Glide.with(itemView).load(IMG_BASE + movie.poster).into(itemView.moviePoster)
        }

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolderView {
        return MovieHolderView(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        ,mListener)
    }

    override fun onBindViewHolder(holder: MovieHolderView, position: Int) {
        holder.bindMovie(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    //getting the id of the movie clicked
    fun getSelectedMovie(position:Int): Movie? {
        if(movies != null){
            if(movies.isNotEmpty()){
                return movies[position]
            }
        }
        return null
    }
}