package com.app.upcomingmovies.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.upcomingmovies.response.MovieImage

@Dao
interface MovieImagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieImages(images: List<MovieImage>)

    @Query("SELECT * FROM movie_image WHERE movieId=:movieId")
    suspend fun getMovieImages(movieId: Long): List<MovieImage>

}