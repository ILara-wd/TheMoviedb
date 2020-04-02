package com.shopperpos.movie.main

import android.view.View
import android.app.Activity
import android.view.ViewGroup
import com.shopperpos.movie.R
import android.widget.TextView
import android.widget.ImageView
import android.view.LayoutInflater
import com.shopperpos.movie.tools.MovieTools
import androidx.recyclerview.widget.RecyclerView
import com.shopperpos.movie.service.MovieConstants
import androidx.constraintlayout.widget.ConstraintLayout
import com.shopperpos.movie.service.data.movieGenre.Movie

class MainAdapter(private val mActivity: Activity, private val items: List<Movie>, private val listener: (Movie) -> Unit) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_main_item, parent, false)
        return MainViewHolder(v)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = items[position]
        holder.tvTitle.text = movie.title
        MovieTools().showImageByUrl(MovieConstants.IMAGE_URL+movie.poster_path.toString(), holder.ivMoviePoster, mActivity)
        holder.itemContainer.setOnClickListener { listener(movie) }
    }

    override fun getItemCount(): Int = items.size

    class MainViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivMoviePoster: ImageView = itemView.findViewById(R.id.iv_movie_poster)
        var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        var itemContainer : ConstraintLayout = itemView.findViewById(R.id.item_container)
    }

}