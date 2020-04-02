package com.shopperpos.movie.main

import com.shopperpos.movie.service.data.movieGenre.Movie

sealed class MainState {
    class ShowItems(val items: List<Movie>) : MainState()
    class ShowMessage(val message: String) : MainState()
}