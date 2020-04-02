package com.shopperpos.movie.movieDetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shopperpos.movie.R
import com.shopperpos.movie.service.WebServicesCompendium.getInfoMovie
import com.shopperpos.movie.service.data.movieGenre.Movie

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movie = intent.getSerializableExtra("movie") as Movie
        getInfoMovie(movie.id.toString().toInt())

    }
}