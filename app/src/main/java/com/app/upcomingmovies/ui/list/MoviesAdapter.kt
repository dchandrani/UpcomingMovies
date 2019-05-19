package com.app.upcomingmovies.ui.list

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.upcomingmovies.R
import com.app.upcomingmovies.response.Movie
import com.app.upcomingmovies.util.inflate
import com.app.upcomingmovies.util.setImage

class MoviesAdapter(
    private val movies: List<Movie>,
    private val onMovieClicked: (movieId: Long, title: String) -> Unit
) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(parent.inflate(R.layout.item_movie))

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])

        holder.itemView.setOnClickListener {
            onMovieClicked(movies[position].id, movies[position].title)
        }
    }

    override fun getItemCount(): Int = movies.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val movieName = itemView.findViewById<TextView>(R.id.tvMovieName)
        private val posterImage = itemView.findViewById<ImageView>(R.id.ivPoster)
        private val releaseDate = itemView.findViewById<TextView>(R.id.tvReleaseDate)
        private val contentRating = itemView.findViewById<TextView>(R.id.tvRating)

        fun bind(movie: Movie) {
            movieName.text = movie.title
            posterImage.setImage(movie.getPoster())
            releaseDate.text = movie.releaseData
            contentRating.text = movie.contentRating
        }
    }
}