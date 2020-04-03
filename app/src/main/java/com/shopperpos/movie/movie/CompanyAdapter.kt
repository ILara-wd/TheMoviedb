package com.shopperpos.movie.movie

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shopperpos.movie.R
import com.shopperpos.movie.service.MovieConstants
import com.shopperpos.movie.service.data.movieDetail.ProductionCompany
import com.shopperpos.movie.tools.MovieTools

class CompanyAdapter(private val mActivity: Activity, private val items: List<ProductionCompany>) :
    RecyclerView.Adapter<CompanyAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item, parent, false)
        return MainViewHolder(v)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = items[position]
        MovieTools().showImageByUrl(
            MovieConstants.IMAGE_URL + movie.logo_path.toString(),
            holder.ivLogo,
            mActivity
        )
    }

    override fun getItemCount(): Int = items.size

    class MainViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivLogo: ImageView = itemView.findViewById(R.id.iv_logo)
    }

}