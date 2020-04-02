package com.shopperpos.movie.service.data

import com.shopperpos.movie.service.model.APIError
import com.shopperpos.movie.service.model.Session

class SessionOutput {
    var session: Session? = null
    var error: APIError? = null
}