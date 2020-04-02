package com.shopperpos.movie.service

import android.content.Context
import android.widget.Toast
import com.shopperpos.movie.service.data.movieGenre.MovieGenreOutput

object WebServicesCompendium {

    fun test(mContext: Context) {

        val data = MovieGenreOutput()
        data.page = 1
        data.with_genres = 28

        MovieWS.getMovies(data) { error, response ->
            if (error != null){
                print(error)
                Toast.makeText(mContext, error.toString(), Toast.LENGTH_LONG).show()
            }
            if (response != null){
                print(response)
                Toast.makeText(mContext, response.toString(), Toast.LENGTH_LONG).show()
            }
        }

    }

    fun getInfoMovie(movieId: Int) {
        MovieWS.getMovieDetail(movieId) { error, response ->
            if (error != null) {
                print(error)
            }
            if (response != null) {
                print(response)
            }
        }
    }


}