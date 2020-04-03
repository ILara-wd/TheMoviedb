package com.shopperpos.movie.movie

import com.shopperpos.movie.service.data.movieDetail.MovieDetail
import com.shopperpos.movie.service.model.APIError

interface ImplMovieInteract {
    fun getMovieDetail(response: MovieDetail)
    fun trowError(error: APIError)
}