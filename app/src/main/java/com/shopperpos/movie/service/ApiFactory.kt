package com.shopperpos.movie.service

import mx.com.gaiadesign.gaia.gaia_service.gaia_retrofit.RetrofitFactory

object ApiFactory {

    val movieApi: MovieApi = RetrofitFactory
        .retrofit("https://api.themoviedb.org/3")
        .create(MovieApi::class.java)

}