package com.shopperpos.movie.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.shopperpos.movie.R
import com.shopperpos.movie.dialogs.MovieDialog
import com.shopperpos.movie.service.data.movieGenre.Movie
import com.shopperpos.movie.tools.ScreenState
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(
            this,
            MainViewModelFactory(FindMoviesInteract())
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
        val mDialogFragment = MovieDialog(movie)
        mDialogFragment.show(supportFragmentManager, "movie")

        //mDialogFragment.showLoading()// Inicia el button loader

        //mDialogFragment.showButtonText(false)// Termina el button loader
        /*mDialogFragment.dismiss()// Termina el dialog*/
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

    open class GaiaModel : Serializable {

        override fun toString(): String {
            return Gson().toJson(this)
        }

        fun <T : GaiaModel> toEntity(entityClass: Class<T>): T {
            val gson = Gson()
            return Gson().fromJson(gson.toJson(this), entityClass)
        }
    }

}
