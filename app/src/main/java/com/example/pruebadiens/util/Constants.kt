package com.example.pruebadiens.util

class Constants {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/"
        const val POPULAR_MOVIES = "3/movie/popular?api_key=ec0476e5f8cf8d29d8a923872942752b&language=en-US&page=1"
        const val TOP_RATED = "3/movie/top_rated?api_key=ec0476e5f8cf8d29d8a923872942752b&language=en-US&page=1"
        const val UPCOMING = "3/movie/upcoming?api_key=ec0476e5f8cf8d29d8a923872942752b&language=en-US&page=1"
        const val URL_IMAGES = "https://image.tmdb.org/t/p/w500/"
        const val GENRE ="3/genre/movie/list?api_key=ec0476e5f8cf8d29d8a923872942752b&language=en-US"
        const val VIDEO ="/videos?api_key=ec0476e5f8cf8d29d8a923872942752b&language=en-US"
    }
}