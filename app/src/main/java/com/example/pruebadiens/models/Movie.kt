package com.example.pruebadiens.models

data class Movie(
    var poster_path: String,
    var popularity: Double,
    var vote_count: Double,
    var video: Boolean,
    var media_type: String,
    var id: Double,
    var adult: Boolean,
    var backdrop_path: String,
    var original_language: String,
    var original_title: String,
    var genre_ids: Array<Int>,
    var title: String,
    var vote_average: Double,
    var overview: String,
    var release_date: String
)