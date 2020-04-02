package com.shopperpos.movie.dialogs

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.shopperpos.movie.R
import com.shopperpos.movie.service.MovieConstants
import com.shopperpos.movie.service.data.movieGenre.Movie
import com.shopperpos.movie.tools.MovieTools

class MovieDialog(private val movie: Movie) : DialogFragment() {

    private lateinit var tvRating: TextView
    private lateinit var tvDialogTitle: TextView
    private lateinit var tvDialogReleaseDate: TextView
    private lateinit var tvDialogOverride: TextView
    private lateinit var ivPosterDialog: ImageView

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view: View = activity?.layoutInflater!!.inflate(R.layout.movie_dialog, null)
        initView(view)
        val alert = AlertDialog.Builder(activity)
        alert.setView(view)
        return alert.create()
    }

    private fun initView(view: View) {
        tvRating = view.findViewById(R.id.tv_rating)
        tvDialogTitle = view.findViewById(R.id.tv_dialog_title)
        tvDialogReleaseDate = view.findViewById(R.id.tv_dialog_release_date)
        tvDialogOverride = view.findViewById(R.id.tv_dialog_override)
        ivPosterDialog = view.findViewById(R.id.iv_poster_dialog)
        showMovieData()
    }

    private fun showMovieData(){
        tvDialogTitle.text = movie.title.toString()
        tvRating.text = getString(R.string.text_rating, movie.vote_average.toString())
        tvDialogReleaseDate.text = movie.release_date.toString()
        tvDialogOverride.text = getString(R.string.text_overview, movie.overview.toString())

        activity?.let {
            MovieTools().showImageByUrl(
                MovieConstants.IMAGE_URL+movie.poster_path.toString(),
                ivPosterDialog,
                it
            )
        }

    }

}