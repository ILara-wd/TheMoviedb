package com.shopperpos.movie.movie

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.shopperpos.movie.R
import com.shopperpos.movie.service.MovieConstants
import com.shopperpos.movie.service.data.movieDetail.MovieDetail
import com.shopperpos.movie.service.data.movieGenre.Movie
import com.shopperpos.movie.tools.MovieTools
import com.shopperpos.movie.tools.ScreenState
import kotlinx.android.synthetic.main.activity_movie.*

class MovieActivity : AppCompatActivity() {

    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        val movie = intent.getSerializableExtra("movie") as Movie

        viewModel = ViewModelProviders.of(
            this@MovieActivity,
            MovieViewModelFactory(MovieInteract(), movie.id.toString().toInt())
        )[MovieViewModel::class.java]

        viewModel.movieState.observe(::getLifecycle, ::updateUI)

    }

    private fun updateUI(screenState: ScreenState<MovieState>?) {
        when (screenState) {
            ScreenState.Loading -> showProgress()
            is ScreenState.Render -> processRenderState(screenState.renderState)
        }
    }

    private fun hideProgress() {
        progress.visibility = View.GONE
    }

    private fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    private fun processRenderState(renderState: MovieState) {
        hideProgress()
        when (renderState) {
            is MovieState.ShowInfoMovie -> setMovieInfo(renderState.movieDetail)
            is MovieState.ShowMessage -> showMessage(renderState.message)
        }
    }

    private fun setMovieInfo(movieDetail: MovieDetail) {
        MovieTools().showImageByUrl(
            MovieConstants.IMAGE_URL + movieDetail.poster_path,
            iv_movie_poster,
            this@MovieActivity
        )
        tv_movie_title.text = movieDetail.title
        //tv_movie_productions_companies.text = ""
        //rv_movie_productions_companies.text = ""
        tv_movie_override.text = getString(R.string.text_overview, movieDetail.overview)
        tv_movie_language.text =
            getString(R.string.text_tv_original_language, movieDetail.original_language)
        tv_movie_budget.text = getString(R.string.text_tv_budget, movieDetail.budget.toString())
        tv_movie_revenue.text = getString(R.string.text_tv_revenue, movieDetail.revenue.toString())
        tv_movie_release_date.text =
            getString(R.string.text_tv_release_date, movieDetail.release_date.toString())
        tv_movie_runtime.text = getString(R.string.text_tv_runtime, movieDetail.runtime.toString())
        tv_movie_vote_average.text =
            getString(R.string.text_rating, movieDetail.vote_average.toString())
        tv_movie_vote_count.text =
            getString(R.string.text_tv_vote_count, movieDetail.vote_count.toString())

        val genres = ""
        movieDetail.genres?.forEach { genres.plus(it.name + " ") }
        tv_movie_genres.text = genres

    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        finish()
    }

}