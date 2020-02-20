package com.app.upcomingmovies.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class MovieImagesResponse(val backdrops: List<MovieImage>)

@Entity(tableName = "movie_image")
data class MovieImage(@PrimaryKey @SerializedName("file_path") val filePath: String, @ColumnInfo(name = "movieId") var id: Long) {
    fun getImagePath() = "http://image.tmdb.org/t/p/w185/$filePath"
}