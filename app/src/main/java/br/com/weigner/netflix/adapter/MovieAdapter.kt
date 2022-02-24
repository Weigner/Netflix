package br.com.weigner.netflix.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.weigner.netflix.MovieActivity
import br.com.weigner.netflix.R
import br.com.weigner.netflix.listeners.OnItemClickListener
import br.com.weigner.netflix.model.MovieModel
import br.com.weigner.netflix.util.ImageDownloaderTask
import br.com.weigner.netflix.viewHolder.MovieViewHolder

class MovieAdapter(movies: MutableList<MovieModel>, private val context: Context) :
    RecyclerView.Adapter<MovieViewHolder>(), OnItemClickListener {

    private var movies: List<MovieModel> = movies

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)

        return MovieViewHolder(view, this)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        var movie = movies[position]
        ImageDownloaderTask(holder.imageViewCover).execute(movie.coverUrl)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onClick(position: Int) {
        if (movies[position].id <= 3) {
            val intent = Intent(context, MovieActivity::class.java)
            intent.putExtra("id", movies[position].id)
            context.startActivity(intent)
        } else {
            Toast.makeText(context, "Filme indisponivel no servidor", Toast.LENGTH_SHORT).show()
        }
    }
}