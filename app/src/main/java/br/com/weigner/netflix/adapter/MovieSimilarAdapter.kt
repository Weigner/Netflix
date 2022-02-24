package br.com.weigner.netflix.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.weigner.netflix.R
import br.com.weigner.netflix.model.MovieModel
import br.com.weigner.netflix.util.ImageDownloaderTask
import br.com.weigner.netflix.viewHolder.MoviesSimilarViewHolder

class MovieSimilarAdapter(private var movies: MutableList<MovieModel>) :
    RecyclerView.Adapter<MoviesSimilarViewHolder>() {

    fun setMovies(movies: MutableList<MovieModel>) {
        this.movies.clear()
        this.movies.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesSimilarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item_similar, parent, false)

        return MoviesSimilarViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesSimilarViewHolder, position: Int) {
        val movie = movies[position]
        ImageDownloaderTask(holder.imageViewCover).execute(movie.coverUrl)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}