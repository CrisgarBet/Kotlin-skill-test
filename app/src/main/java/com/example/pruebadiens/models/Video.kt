package com.example.pruebadiens.models


import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("id") var id: Double,
    @SerializedName("iso_639_1") var iso_639_1: String,
    @SerializedName("iso_3166_1") var iso_3166_1: String,
    @SerializedName("key") var key: String,
    @SerializedName("name") var name: String,
    @SerializedName("site") var site: String,
    @SerializedName("size") var size: Double,
    @SerializedName("type") var type: String
)