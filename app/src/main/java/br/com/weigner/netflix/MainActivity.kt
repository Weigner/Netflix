package br.com.weigner.netflix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.weigner.netflix.adapter.MainAdapter
import br.com.weigner.netflix.model.CategoryModel
import br.com.weigner.netflix.model.MovieModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view_main)

        val categories = mutableListOf<CategoryModel>()

        var j = 0
        while (j < 10) {
            val category = CategoryModel()
            category.name = "cat $j"
            j++

            val movies = mutableListOf<MovieModel>()
            var i = 0
            while (i < 30) {
                var movie = MovieModel()
                movie.coverUrl = "R.drawable.placeholder_bg"
                movies.add(movie)
                i++
            }
            category.movies = movies
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







