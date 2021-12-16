package com.fsociety.moviesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.fsociety.moviesapp.adapters.MovieAdapter
import com.fsociety.moviesapp.models.Movie
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        getDataFromIntent()

    }

    private fun getDataFromIntent() {
        if(intent.hasExtra("movie")){
            val movie = intent.getParcelableExtra<Movie>("movie")

            movieTitle.text = movie?.title
            dateTextView.text = movie?.release_date
            languageTextView.text = movie?.language
            detailsTextView.text = movie?.overview

            Glide.with(this).load(MovieAdapter.IMG_BASE+ movie?.poster).into(movieImage)

        }
    }
}