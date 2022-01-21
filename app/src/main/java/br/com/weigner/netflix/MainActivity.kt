package br.com.weigner.netflix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.weigner.netflix.adapter.MainAdapter
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
                movie.setCoverUrl(R.drawable.placeholder_bg)
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







