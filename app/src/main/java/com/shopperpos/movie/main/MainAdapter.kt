package com.shopperpos.movie.main

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.shopperpos.movie.R
import com.shopperpos.movie.service.data.movieGenre.Movie

class MainAdapter(private val items: List<Movie>, private val listener: (String) -> Unit) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_main_item, parent, false) as TextView

        return MainViewHolder(v)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = items[position]
        holder.textView.text = item.title
        holder.textView.setOnClickListener { listener(item.title.toString()) }
    }

    override fun getItemCount(): Int = items.size

    class MainViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

}