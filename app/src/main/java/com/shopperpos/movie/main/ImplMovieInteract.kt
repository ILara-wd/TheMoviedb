package com.shopperpos.movie.main

import com.shopperpos.movie.service.data.movieGenre.Movie
import com.shopperpos.movie.service.model.APIError

interface ImplMovieInteract {

    fun getResponseData(data: List<Movie>?)
    fun trowError(error: APIError)

}