package br.com.weigner.netflix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.weigner.netflix.model.Movie

class MainActivity : AppCompatActivity() {

    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_main)

        val movies = mutableListOf<Movie>()
        /*for (movie in movies) {
            var movie = Movie()
            movie.setCoverUrl("abc")
            movies.add(movie)
        }*/
        var i = 0
        while (i < 30) {
            var movie = Movie()
            movie.setCoverUrl("abc$i")
            movies.add(movie)
            i++
        }

        mainAdapter = MainAdapter(movies)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = mainAdapter
    }
}

private class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var textViewUrl: TextView = itemView.findViewById<TextView>(R.id.text_view_url)

}

private class MainAdapter(movies: MutableList<Movie>) : RecyclerView.Adapter<MovieViewHolder>() {

    private var movies: List<Movie> = movies

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        var movie = movies.get(position)
        holder.textViewUrl.text = movie.getCoverUrl()
    }

    override fun getItemCount(): Int {
        return movies.size
    }

}