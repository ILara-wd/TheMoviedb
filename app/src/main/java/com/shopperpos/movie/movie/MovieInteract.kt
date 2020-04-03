package com.shopperpos.movie.movie

import com.shopperpos.movie.service.MovieWS

class MovieInteract {

    fun getMovies(mImplMovieInteract: ImplMovieInteract, movieId: Int) {

        MovieWS.getMovieDetail(movieId) { error, response ->
            if (error != null) {
                mImplMovieInteract.trowError(error)
                return@getMovieDetail
            }
            if (response != null) {
                mImplMovieInteract.getMovieDetail(response)
            }
        }

    }

}