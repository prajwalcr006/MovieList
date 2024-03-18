package com.example.movielist.ui.movie_list

import com.example.movielist.domain.model.MovieList

data class MovieListState(
    var isLoading: Boolean = true,
    var movies: List<MovieList> = emptyList(),
    var error: String = ""
)
