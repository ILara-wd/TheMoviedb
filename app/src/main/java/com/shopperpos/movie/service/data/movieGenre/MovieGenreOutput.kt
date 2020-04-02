package com.shopperpos.movie.service.data.movieGenre

import com.shopperpos.movie.model.MovieMode

class MovieGenreOutput : MovieMode() {

    var page: Int? = null
    var with_genres: Int? = null

    init {
        this.page = 1
        this.with_genres = 0
    }

}