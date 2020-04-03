package com.shopperpos.movie.tools

import android.app.Activity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.shopperpos.movie.R

class MovieTools{

    fun showImageByUrl(_url: String, mImageView: ImageView, mActivity: Activity){
        Glide.with(mActivity).load(_url).into(mImageView)
            .apply {
                RequestOptions
                    .placeholderOf(R.drawable.movie_placeholder)
                    .error(R.drawable.movie_placeholder)
            }
    }

    fun numberFormatCash(amount: Double?): String {
        return "$" + String.format("%,.2f", amount)
    }

}