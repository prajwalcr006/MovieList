package com.example.movielist.data.repository

import com.example.movielist.data.remote.dto.Result
import com.example.movielist.domain.model.MovieList

fun Result.toMovieList(): MovieList {
    return MovieList(
        adult = adult,
        originalLanguage = originalLanguage,
        posterPath = posterPath,
        title = title,
        overview = overview,
        releaseDate = releaseDate,
        voteAverage = voteAverage
    )
}