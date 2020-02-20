package com.app.upcomingmovies.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class MovieResponse(val results: List<Movie>)

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey val id: Long,
    val title: String,
    @ColumnInfo(name = "poster_path") @SerializedName("poster_path") val posterPath: String,
    @ColumnInfo(name = "release_date") @SerializedName("release_date") val releaseData: String,
    val adult: Boolean,
    val overview: String
) {
    val contentRating: String
        get() = if (adult) {
            "(A)"
        } else {
            "(U/A)"
        }

    fun getPoster(): String = "http://image.tmdb.org/t/p/w185/$posterPath"
}