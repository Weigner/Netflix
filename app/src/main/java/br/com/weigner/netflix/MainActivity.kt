package br.com.weigner.netflix

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.weigner.netflix.model.Category
import br.com.weigner.netflix.model.Movie

class MainActivity : AppCompatActivity() {

    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_main)

        val categories = mutableListOf<Category>()

        var j = 0
        while (j < 10) {
            var category = Category()
            category.setName("cat $j")
            categories.add(category)
            j++

            val movies = mutableListOf<Movie>()
            var i = 0
            while (i < 30) {
                var movie = Movie()
                movie.setCoverUrl(R.drawable.movie)
                movies.add(movie)
                i++
            }
            category.setMovies(movies)
            categories.add(category)
        }
        /*for (movie in movies) {
            var movie = Movie()
            movie.setCoverUrl("abc")
            movies.add(movie)
        }*/

        mainAdapter = MainAdapter(categories)
        recyclerView.layoutManager = LinearLayoutManager(baseContext, RecyclerView.VERTICAL, false)
        recyclerView.adapter = mainAdapter
    }
}

private class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val textTitle = itemView.findViewById<TextView>(R.id.text_view_title)
    val recyclerViewMovie = itemView.findViewById<RecyclerView>(R.id.recycler_view_movie)

}

private class MainAdapter(categories: MutableList<Category>) :
    RecyclerView.Adapter<CategoryViewHolder>() {

    private var categories: List<Category> = categories

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item, parent, false)

        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        var category = categories.get(position)
        holder.textTitle.text = category.getName()
        holder.recyclerViewMovie.adapter =
            MovieAdapter(category.getMovies() as MutableList<Movie>)
        holder.recyclerViewMovie.layoutManager =
            LinearLayoutManager(null, RecyclerView.HORIZONTAL, false)
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}

private class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var imageViewCover: ImageView = itemView.findViewById(R.id.image_view_cover)

}

private class MovieAdapter(movies: MutableList<Movie>) :
    RecyclerView.Adapter<MovieViewHolder>() {

    private var movies: List<Movie> = movies

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