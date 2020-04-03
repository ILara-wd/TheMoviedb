package com.shopperpos.movie.main

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shopperpos.movie.R
import com.shopperpos.movie.service.MovieConstants
import com.shopperpos.movie.service.data.movieGenre.Movie
import com.shopperpos.movie.tools.MovieTools

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
        holder.tvTitle.setOnClickListener { listener(movie) }
        MovieTools().showImageByUrl(MovieConstants.IMAGE_URL+movie.poster_path.toString(), holder.ivMoviePoster, mActivity)
    }

    override fun getItemCount(): Int = items.size

    class MainViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivMoviePoster: ImageView = itemView.findViewById(R.id.iv_movie_poster)
        var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
    }

}