package com.shopperpos.movie.movie

import com.shopperpos.movie.service.data.movieDetail.MovieDetail

sealed class MovieState {
    class ShowInfoMovie(val movieDetail: MovieDetail) : MovieState()
    class ShowMessage(val message: String) : MovieState()
}