package com.shopperpos.movie.service.data.movieGenre

import com.shopperpos.movie.main.MainActivity

class MovieGenreOutput : MainActivity.GaiaModel() {

    var page: Int? = null
    var with_genres: Int? = null

    init {
        this.page = 1
        this.with_genres = 0
    }

}