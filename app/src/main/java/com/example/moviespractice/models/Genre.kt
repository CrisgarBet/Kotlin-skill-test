package com.example.moviespractice.models

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("genres") var genres: MutableList<Item>
)