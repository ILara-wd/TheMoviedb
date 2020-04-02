package com.shopperpos.movie.service.model

class Session {

    var success: Boolean? = null
    var guest_session_id: String? = null
    var expires_at: String? = null

    init {
        this.success = false
        this.guest_session_id = ""
        this.expires_at = ""
    }

    constructor(guest_session_id: String?, expires_at: String?, success: Boolean?) {
        this.success = success
        this.guest_session_id = guest_session_id
        this.expires_at = expires_at
    }

}