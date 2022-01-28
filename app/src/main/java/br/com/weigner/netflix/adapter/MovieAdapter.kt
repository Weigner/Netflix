package br.com.weigner.netflix.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.weigner.netflix.R
import br.com.weigner.netflix.model.MovieModel
import br.com.weigner.netflix.viewHolder.MovieViewHolder

class MovieAdapter(movies: MutableList<MovieModel>) :
    RecyclerView.Adapter<MovieViewHolder>() {

    private var movies: List<MovieModel> = movies

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        var movie = movies.get(position)
        holder.imageViewCover.setImageResource(movie.getCoverUrl())
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}