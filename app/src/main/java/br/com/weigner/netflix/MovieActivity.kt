package br.com.weigner.netflix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar

class MovieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        if (supportActionBar != null){
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }


    }
}