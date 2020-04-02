package com.shopperpos.movie.service

import com.shopperpos.movie.service.data.SessionOutput
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface MovieApi {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("/authentication/guest_session/new?api_key=")
    fun getSessionIdAsync(): Deferred<Response<SessionOutput>>

}