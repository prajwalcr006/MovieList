package com.example.movielist.domain.repository

import com.example.cryptocurrencyapp.common.Resource
import com.example.movielist.domain.model.MovieList

interface MovieRepository {
    suspend fun getMovieList(): Resource<List<MovieList>>
}