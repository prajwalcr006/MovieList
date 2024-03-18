package com.example.movielist.ui.movie_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.common.Resource
import com.example.movielist.domain.use_case.GetMoviesUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MovieListViewModel @Inject constructor(private val getMoviesUseCase: GetMoviesUseCase): ViewModel() {

    private val _state = MutableLiveData<MovieListState>()
    val state: LiveData<MovieListState> = _state

    init {
        getData()
    }

    private fun getData() {
        val list = getMoviesUseCase()

        list.onEach { result ->
            when(result) {
                is Resource.Success ->
                    _state.value = MovieListState(movies = result.data?: emptyList())

                is Resource.Error ->
                    _state.value = MovieListState(error = result.message?:"Error at view Model")

                is Resource.Loading ->
                    _state.value = MovieListState(isLoading = true)
            }
        }.launchIn(viewModelScope)
    }
}