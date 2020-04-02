package com.shopperpos.movie.service.data.movieDetail

class MovieDetail {
    val vote_count: Int? = null
    val poster_path: String? = null
    val backdrop_path: String? = null
    val original_language: String? = null
    val original_title: String? = null
    val genre_ids: List<Int>? = null
    val vote_average: Double? = null
    val release_date: String? = null
    val adult: Boolean? = null
    val backdropPath: String? = null
    val belongsToCollection: BelongsToCollection? = null
    val budget: Int? = null
    val genres: List<Genre>? = null
    val homepage: String? = null
    val id: Int? = null
    val imdbId: String? = null
    val originalLanguage: String? = null
    val originalTitle: String? = null
    val overview: String? = null
    val popularity: Double? = null
    val posterPath: String? = null
    val productionCompanies: List<ProductionCompany>? = null
    val releaseDate: String? = null
    val revenue: Int? = null
    val runtime: Int? = null
    val status: String? = null
    val tagline: String? = null
    val title: String? = null
    val video: Boolean? = null
    val voteAverage: Double? = null
    val voteCount: Int? = null
}

class Genre {
    var id: Int? = null
    var name: String? = null
}

class ProductionCompany {
    var id: Int? = null
    var logoPath: String? = null
    var name: String? = null
    var originCountry: String? = null
}

class BelongsToCollection {
    var id: Int? = null
    var name: String? = null
    var posterPath: String? = null
    var backdropPath: String? = null
}
