package com.example.moviespractice.api

import com.example.moviespractice.models.Genre
import com.example.moviespractice.models.Movie
import com.example.moviespractice.models.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiServiceInterface {

    @GET
    fun getAllMovies(@Url url:String): Call<Movie>

    @GET
    fun getAllGenre(@Url url:String): Call<Genre>

    @GET
    fun getVideo(@Url url:String): Call<Response>

}