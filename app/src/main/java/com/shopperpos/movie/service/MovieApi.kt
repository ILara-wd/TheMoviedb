package com.shopperpos.movie.service

import com.shopperpos.movie.service.data.SessionOutput
import com.shopperpos.movie.service.data.movieDetail.MovieDetail
import com.shopperpos.movie.service.data.movieGenre.MovieGenreInput
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("authentication/guest_session/new?api_key=${MovieConstants.API_KEY}")
    fun getSessionIdAsync(): Deferred<Response<SessionOutput>>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("genre/movie/list?api_key=${MovieConstants.API_KEY}&language=${MovieConstants.LANGUAGE}")
    fun getGenreMovieAsync(): Deferred<Response<SessionOutput>>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("genre/tv/list?api_key=${MovieConstants.API_KEY}&language=${MovieConstants.LANGUAGE}")
    fun getGenreTvAsync(): Deferred<Response<SessionOutput>>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("discover/movie?sort_by=popularity.desc&api_key=${MovieConstants.API_KEY}&language=${MovieConstants.LANGUAGE}")
    fun getMoviesByGenreAsync(@Query("page") page: String, @Query("with_genres") with_genres: String )
            : Deferred<Response<MovieGenreInput>>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("movie/{movie_id}?api_key=${MovieConstants.API_KEY}&language=${MovieConstants.LANGUAGE}")
    fun getMovieInfoDetailAsync(@Path("movie_id") movie_id: Int): Deferred<Response<MovieDetail>>

}