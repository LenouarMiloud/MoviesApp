package com.fsociety.moviesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fsociety.moviesapp.adapters.MovieAdapter
import com.fsociety.moviesapp.models.Movie
import com.fsociety.moviesapp.models.ResponseMovie
import com.fsociety.moviesapp.services.MovieAPI
import com.fsociety.moviesapp.services.MovieAPIService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerMoviesList.layoutManager = LinearLayoutManager(this)
        recyclerMoviesList.setHasFixedSize(true)
        getMovieData { movies : List<Movie> ->
            var adapter = MovieAdapter(movies)
            recyclerMoviesList.adapter = adapter
            //Lunch the Details Activity when we choose one movie
            adapter.setOnItemClickListener(object : MovieAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    intent = Intent(applicationContext,DetailsActivity::class.java)
                    intent.putExtra("movie",adapter.getSelectedMovie(position))
                    startActivity(intent)
                }
            })
        }
    }

    private fun getMovieData(callback:(List<Movie>)-> Unit){
        val apiService = MovieAPIService.getInstance().create(MovieAPI::class.java)
        apiService.getListMovie().enqueue(object : Callback<ResponseMovie>{
            override fun onResponse(call: Call<ResponseMovie>, response: Response<ResponseMovie>) {
                return callback(response.body()!!.movies)
            }
            override fun onFailure(call: Call<ResponseMovie>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}