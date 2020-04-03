@file:Suppress("BlockingMethodInNonBlockingContext")
package com.shopperpos.movie.service

import com.google.gson.Gson
import com.shopperpos.movie.service.data.SessionOutput
import com.shopperpos.movie.service.data.movieDetail.MovieDetail
import com.shopperpos.movie.service.data.movieGenre.MovieGenreInput
import com.shopperpos.movie.service.data.movieGenre.MovieGenreOutput
import com.shopperpos.movie.service.model.APIError
import com.shopperpos.movie.service.model.Session
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object MovieWS {

    private suspend fun movieGetSession(): Pair<APIError?, Session?> {

        try {
            val movieApi = ApiFactory.movieApi
            val request = movieApi.getSessionIdAsync()
            val response = request.await()
            if (!response.isSuccessful) {
                val error = Gson().fromJson(response.errorBody()?.string(), APIError::class.java)
                return Pair(error, null)
            }
            val output: SessionOutput? = response.body()

            if (output?.success == null) {
                return Pair(APIError(666, "The service didn't return a session", false), null)
            }
            return Pair(null, Session(output.guest_session_id, output.expires_at, output.success))

        } catch (e: Exception) {
            return Pair(APIError(999, e.message, false), null)
        }

    }

    fun getMovies(data: MovieGenreOutput, handler: (error: APIError?, response: MovieGenreInput?) -> Unit) {
        val movieApi = ApiFactory.movieApi

        GlobalScope.launch(Dispatchers.Main) {
            try {
/*                val (tokenError, token) = movieGetSession()
                if (tokenError != null) {
                    handler(tokenError, null)
                    return@launch
                }
*/
                val request = movieApi.getMoviesByGenreAsync(data.page.toString(), data.with_genres.toString())
                val response = request.await()

                if (!response.isSuccessful) {
                    val error = Gson().fromJson(response.errorBody()?.string(), APIError::class.java)
                    handler(error, null)
                    return@launch
                }

                val body: MovieGenreInput? = response.body()

                if (body?.total_results.toString().toInt() < 0) {
                    handler(APIError(678,"no movies available", false), null)
                    return@launch
                }

                handler(null, body)

            } catch (e: java.lang.Exception) {
                handler(APIError(e.hashCode(), e.localizedMessage, false), null)
            }
        }

    }

    fun getMovieDetail(movieId: Int, handler: (error: APIError?, response: MovieDetail?) -> Unit) {
        val movieApi = ApiFactory.movieApi

        GlobalScope.launch(Dispatchers.Main) {
            try {

                val request = movieApi.getMovieInfoDetailAsync(movieId)
                val response = request.await()

                if (!response.isSuccessful) {
                    val error =
                        Gson().fromJson(response.errorBody()?.string(), APIError::class.java)
                    handler(error, null)
                    return@launch
                }

                val body: MovieDetail? = response.body()

                if (body == null) {
                    handler(APIError(678, "no movie available", false), null)
                    return@launch
                }

                handler(null, body)

            } catch (e: java.lang.Exception) {
                handler(APIError(e.hashCode(), e.localizedMessage, false), null)
            }
        }

    }

}