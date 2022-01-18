package br.com.weigner.netflix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.weigner.netflix.model.Movie

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

private class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}

private class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private lateinit var movies: List<Movie>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        var movie = movies.get(position)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}