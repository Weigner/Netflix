package br.com.weigner.netflix

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.weigner.netflix.adapter.MovieAdapter
import br.com.weigner.netflix.adapter.MovieSimilarAdapter
import br.com.weigner.netflix.model.MovieDetail
import br.com.weigner.netflix.model.MovieModel
import br.com.weigner.netflix.listeners.MovieDetailLoader
import br.com.weigner.netflix.util.ImageDownloaderTask
import br.com.weigner.netflix.util.MovieDetailTask

class MovieActivity : AppCompatActivity(), MovieDetailLoader {

    private lateinit var textTitle: TextView
    private lateinit var textDescription: TextView
    private lateinit var textCast: TextView
    private lateinit var imageCover: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieSimilarAdapter: MovieSimilarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        textTitle = findViewById(R.id.text_view_title)
        textDescription = findViewById(R.id.text_view_description)
        textCast = findViewById(R.id.text_view_cast)
        imageCover = findViewById(R.id.image_view_cover)
        recyclerView = findViewById(R.id.recycler_view_similar)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar?.title = null

        var movies: MutableList<MovieModel> = mutableListOf()

        movieSimilarAdapter = MovieSimilarAdapter(movies)
        recyclerView.adapter = movieSimilarAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 3)

        val id = intent.extras?.getInt("id")
        val movieDetailTask = MovieDetailTask(this)
        movieDetailTask.setMovieDetailLoader(this)
        movieDetailTask.execute("https://tiagoaguiar.co/api/netflix/${id}")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResult(movieDetail: MovieDetail) {
        textTitle.text = movieDetail.movie.title
        textDescription.text = movieDetail.movie.description
        textCast.text = movieDetail.movie.cast

        val imageDownloaderTask = ImageDownloaderTask(imageCover)
        imageDownloaderTask.setShadowEnabled(true)
        imageDownloaderTask.execute(movieDetail.movie.coverUrl)

        movieSimilarAdapter.setMovies(movieDetail.moviesSimilar)
        movieSimilarAdapter.notifyDataSetChanged()
    }
}



