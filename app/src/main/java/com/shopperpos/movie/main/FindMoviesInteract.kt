package com.shopperpos.movie.main

import com.shopperpos.movie.service.MovieWS
import com.shopperpos.movie.service.data.movieGenre.MovieGenreOutput

class FindMoviesInteract {

    fun getMovies(mImplMovieInteract: ImplMovieInteract) {

        val data = MovieGenreOutput()
        data.page = 1
        data.with_genres = 28

        MovieWS.getMovies(data) { error, response ->
            if (error != null){
                mImplMovieInteract.trowError(error)
                return@getMovies
            }
            if (response != null){
                mImplMovieInteract.getResponseData(response.results)
            }
        }

    }

}