package com.example.pruebadiens.models

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String
)