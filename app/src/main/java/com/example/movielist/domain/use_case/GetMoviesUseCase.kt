package com.example.movielist.domain.use_case

import com.example.cryptocurrencyapp.common.Resource
import com.example.movielist.domain.model.MovieList
import com.example.movielist.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    operator fun invoke(): Flow<Resource<List<MovieList>>> = flow {
        emit(movieRepository.getMovieList())
    }

}