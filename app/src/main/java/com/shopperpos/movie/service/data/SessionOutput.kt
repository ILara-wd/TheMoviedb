package com.shopperpos.movie.service.data

import com.shopperpos.movie.model.MovieMode

class SessionOutput : MovieMode() {

    var success: Boolean? = null
    var guest_session_id: String? = null
    var expires_at: String? = null
    var status_code: Int? = null
    var status_message: String? = null

    init {
        this.status_message = ""
        this.guest_session_id = ""
        this.expires_at = ""
        this.success = false
        this.status_code = 0
    }
}