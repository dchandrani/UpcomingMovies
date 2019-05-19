package com.app.upcomingmovies.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(val results: List<Movie>)

data class Movie(val id: Long,
                 val title: String,
                 @SerializedName("poster_path") val posterPath: String,
                 @SerializedName("release_date") val releaseData: String,
                 val adult: Boolean,
                 val overview: String)