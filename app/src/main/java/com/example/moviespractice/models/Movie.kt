package com.example.moviespractice.models

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("page") var page: Int,
    @SerializedName("total_results") var total_results: Double,
    @SerializedName("total_pages") var total_pages: Double,
    @SerializedName("results") var results: MutableList<Result>
)