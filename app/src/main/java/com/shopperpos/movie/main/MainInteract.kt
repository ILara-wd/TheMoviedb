package com.shopperpos.movie.main

import com.shopperpos.movie.service.MovieWS
import com.shopperpos.movie.service.data.movieGenre.MovieGenreOutput

class MainInteract {

    fun getMovies(mImplMainInteract: ImplMainInteract) {

        val data = MovieGenreOutput()
        data.page = 1
        data.with_genres = 28

        MovieWS.getMovies(data) { error, response ->
            if (error != null){
                mImplMainInteract.trowError(error)
            }
            if (response != null){
                mImplMainInteract.getResponseData(response.results)
            }
        }

    }

}