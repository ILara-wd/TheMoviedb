package com.shopperpos.movie.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shopperpos.movie.service.data.movieGenre.Movie
import com.shopperpos.movie.service.model.APIError
import com.shopperpos.movie.tools.ScreenState

class MainViewModel(private val findIMoviesInteract: FindMoviesInteract) : ViewModel(), ImplMovieInteract {

    private lateinit var _mainState: MutableLiveData<ScreenState<MainState>>

    val mainState: LiveData<ScreenState<MainState>>
        get() {
            if (!::_mainState.isInitialized) {
                _mainState = MutableLiveData()
                _mainState.value = ScreenState.Loading
                findIMoviesInteract.getMovies(this)
            }
            return _mainState
        }

    fun onItemClicked(movie: Movie) {
        _mainState.value = ScreenState.Render(MainState.ShowDialogInfo(movie))
    }

    override fun getResponseData(data: List<Movie>?) {
        _mainState.value = ScreenState.Render(MainState.ShowItems(data.orEmpty()))
    }

    override fun trowError(error: APIError) {
        _mainState.value = ScreenState.Render(MainState.ShowMessage(error.status_message.toString()))
    }

}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val findIMoviesInteract: FindMoviesInteract) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(findIMoviesInteract) as T
    }

}