package com.shopperpos.movie.service.model

import com.shopperpos.movie.main.MainActivity

class APIError() : MainActivity.GaiaModel() {

    var status_code: Int? = null
    var status_message: String? = null
    var success: Boolean? = null

    init {
        this.status_code = 0
        this.status_message = ""
        this.success = false
    }

    constructor(code: Int?, message: String?, success: Boolean): this() {
        this.status_code = code
        this.status_message = message
        this.success = success
    }

}