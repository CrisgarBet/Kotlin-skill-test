package com.example.moviespractice.models


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("id") var id: Int,
    @SerializedName("results") var results: MutableList<Video>
)