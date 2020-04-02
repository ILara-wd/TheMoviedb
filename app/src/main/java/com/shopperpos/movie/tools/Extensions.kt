package com.shopperpos.movie.tools

import android.os.Handler

fun postDelayed(delayMillis: Long, task: () -> Unit) {
    Handler().postDelayed(task, delayMillis)
}

infix fun <T : Any> Boolean.ternary(value: T): T? = if(this) value else null