package com.app.upcomingmovies.response

import com.google.gson.annotations.SerializedName

data class MovieImagesResponse(val backdrops: List<MovieImage>)

data class MovieImage(@SerializedName("file_path") private val filePath: String) {
    fun getImagePath() = "http://image.tmdb.org/t/p/w185/$filePath"
}