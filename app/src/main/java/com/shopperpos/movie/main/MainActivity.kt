package com.shopperpos.movie.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.shopperpos.movie.R
import com.shopperpos.movie.dialogs.MovieDialog
import com.shopperpos.movie.dialogs.SetOnDetailListener
import com.shopperpos.movie.movie.MovieActivity
import com.shopperpos.movie.service.data.movieGenre.Movie
import com.shopperpos.movie.tools.ScreenState
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SetOnDetailListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var mDialogFragment: MovieDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(
            this,
            MainViewModelFactory(MainInteract())
        )[MainViewModel::class.java]

        viewModel.mainState.observe(::getLifecycle, ::updateUI)

    }

    private fun updateUI(screenState: ScreenState<MainState>?) {
        when (screenState) {
            ScreenState.Loading -> showProgress()
            is ScreenState.Render -> processRenderState(screenState.renderState)
        }
    }

    private fun processRenderState(renderState: MainState) {
        hideProgress()
        when (renderState) {
            is MainState.ShowItems -> setItems(renderState.items)
            is MainState.ShowMessage -> showMessage(renderState.message)
            is MainState.ShowDialogInfo -> showDialogInfo(renderState.movie)
        }
    }

    private fun showDialogInfo(movie: Movie){
        mDialogFragment = MovieDialog(movie, this)
        mDialogFragment.show(supportFragmentManager, "movie")
    }

    private fun showProgress() {
        progress.visibility = View.VISIBLE
        list.visibility = View.GONE
    }

    private fun hideProgress() {
        progress.visibility = View.GONE
        list.visibility = View.VISIBLE
    }

    private fun setItems(items: List<Movie>) {
        list.layoutManager = GridLayoutManager(this, 4)
        list.adapter = MainAdapter(this, items, viewModel::onItemClicked)
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onClickDetailMovie(movie: Movie) {
        mDialogFragment.dismiss()
        val intent = Intent(this@MainActivity, MovieActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }

}