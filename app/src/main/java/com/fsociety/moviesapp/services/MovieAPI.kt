package com.fsociety.moviesapp.services

import com.fsociety.moviesapp.models.ResponseMovie
import retrofit2.Call
import retrofit2.http.GET

interface MovieAPI {

    @GET("/3/discover/movie?api_key=c9856d0cb57c3f14bf75bdc6c063b8f3")
    fun getListMovie(): Call<ResponseMovie>

}