package com.shopperpos.movie.model

import com.google.gson.Gson
import java.io.Serializable

open class MovieMode : Serializable {

    override fun toString(): String {
        return Gson().toJson(this)
    }

    fun <T : MovieMode> toEntity(entityClass: Class<T>): T {
        val g = Gson()
        return Gson().fromJson(g.toJson(this), entityClass)
    }

}