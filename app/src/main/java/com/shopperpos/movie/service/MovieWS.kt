package com.shopperpos.movie.service

import com.shopperpos.movie.service.data.SessionOutput
import com.shopperpos.movie.service.model.APIError
import com.shopperpos.movie.service.model.Session

object MovieWS {

    suspend fun movieGetSession(): Pair<APIError?, Session?> {
        try {
            val movieApi = ApiFactory.movieApi
            val request = movieApi.getSessionIdAsync()
            val response = request.await()
            if (!response.isSuccessful) {
                return Pair(APIError(response.code(), response.message(), false), null)
            }
            val output: SessionOutput? = response.body()
            if (output?.error != null) {
                return Pair(output.error, null)
            }
            if (output?.session == null) {
                return Pair(APIError(666, "The service didn't return a token", false), null)
            }
            return Pair(null, output.session)
        } catch (e: Exception) {
            return Pair(APIError(), Session())
        }
    }

}