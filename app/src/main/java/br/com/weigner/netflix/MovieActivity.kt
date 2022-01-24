package br.com.weigner.netflix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class MovieActivity : AppCompatActivity() {

    private lateinit var textTitle: TextView
    private lateinit var textDescription: TextView
    private lateinit var textCast: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        textTitle = findViewById(R.id.text_view_title)
        textDescription = findViewById(R.id.text_view_description)
        textCast = findViewById(R.id.text_view_cast)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar?.title = null

        textTitle.text = "The Batman"
        textDescription.text = "Da Warner Bros. Pictures chega THE BATMAN com o realizador Matt Reeves no comando e protagonizado por Robert Pattinson no duplo papel de detetive de Gotham City e do seu alter ego, o bilionário solitário Bruce Wayne."
        textCast.text = getString(R.string.cast, "Robert Pattinson" + "Paul Dano" + "Colin Farrell" + "Jeffrey Wright" + "Andy Serkis")


/*        val drawable: LayerDrawable? =
            ContextCompat.getDrawable(this, R.drawable.shadows) as LayerDrawable?

        val movieCover: Drawable? = ContextCompat.getDrawable(this, R.drawable.movie)
        drawable?.setDrawableByLayerId(R.id.cover_drewble, movieCover)
        (findViewById<ImageView>(R.id.image_view_cover)).setImageDrawable(drawable)*/


    }
}