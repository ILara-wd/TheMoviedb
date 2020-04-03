package com.shopperpos.movie.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shopperpos.movie.service.data.movieDetail.MovieDetail
import com.shopperpos.movie.service.model.APIError
import com.shopperpos.movie.tools.ScreenState

class MovieViewModel(private val mainInteract: MovieInteract, private val movieId: Int) :
    ViewModel(), ImplMovieInteract {

    private lateinit var _mainState: MutableLiveData<ScreenState<MovieState>>

    val movieState: LiveData<ScreenState<MovieState>>
        get() {
            if (!::_mainState.isInitialized) {
                _mainState = MutableLiveData()
                _mainState.value = ScreenState.Loading
                mainInteract.getMovies(this, movieId)
            }
            return _mainState
        }

    override fun getMovieDetail(response: MovieDetail) {
        _mainState.value = ScreenState.Render(MovieState.ShowInfoMovie(response))
    }

    override fun trowError(error: APIError) {
        _mainState.value =
            ScreenState.Render(MovieState.ShowMessage(error.status_message.toString()))
    }

}

@Suppress("UNCHECKED_CAST")
class MovieViewModelFactory(private val mMovieInteract: MovieInteract, private val movieId: Int) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(mMovieInteract, movieId) as T
    }

}