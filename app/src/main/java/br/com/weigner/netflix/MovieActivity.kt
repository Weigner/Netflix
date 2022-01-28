package br.com.weigner.netflix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.weigner.netflix.model.MovieModel

class MovieActivity : AppCompatActivity() {

    private lateinit var textTitle: TextView
    private lateinit var textDescription: TextView
    private lateinit var textCast: TextView
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        textTitle = findViewById(R.id.text_view_title)
        textDescription = findViewById(R.id.text_view_description)
        textCast = findViewById(R.id.text_view_cast)
        recyclerView = findViewById(R.id.recycler_view_similar)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar?.title = null

        textTitle.text = "The Batman"
        textDescription.text = "Da Warner Bros. Pictures chega THE BATMAN com o realizador Matt Reeves no comando e protagonizado por Robert Pattinson no duplo papel de detetive de Gotham City e do seu alter ego, o bilionário solitário Bruce Wayne."
        textCast.text = getString(R.string.cast, "Robert Pattinson" + "Paul Dano" + "Colin Farrell" + "Jeffrey Wright" + "Andy Serkis")

        var movies: MutableList<MovieModel> = mutableListOf()
        var i = 0
        while (i < 30) {
            var movie = MovieModel()
            movies.add(movie)
            i++
        }

        recyclerView.adapter = MovieSimilarAdapter(movies)
        recyclerView.layoutManager = GridLayoutManager(this, 3)




/*        val drawable: LayerDrawable? =
            ContextCompat.getDrawable(this, R.drawable.shadows) as LayerDrawable?

        val movieCover: Drawable? = ContextCompat.getDrawable(this, R.drawable.movie)
        drawable?.setDrawableByLayerId(R.id.cover_drewble, movieCover)
        (findViewById<ImageView>(R.id.image_view_cover)).setImageDrawable(drawable)*/
    }
}

class MovieSimilarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var imageViewCover: ImageView = itemView.findViewById(R.id.image_view_similar_cover)

}

class MovieSimilarAdapter(movies: MutableList<MovieModel>) :
    RecyclerView.Adapter<MovieSimilarViewHolder>() {

    private var movies: List<MovieModel> = movies

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSimilarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item_similar, parent, false)

        return MovieSimilarViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieSimilarViewHolder, position: Int) {
        var movie = movies.get(position)
//        holder.imageViewCover.setImageResource(movie.getCoverUrl())
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}