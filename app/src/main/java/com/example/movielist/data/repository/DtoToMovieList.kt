package com.example.movielist.data.repository

import android.util.Log
import com.example.movielist.data.remote.dto.Result
import com.example.movielist.domain.model.MovieList

fun Result.toMovieList(): MovieList {

    return MovieList(
        adult = adult,
        originalLanguage = original_language,
        posterPath = poster_path,
        title = title,
        overview = overview,
        releaseDate = release_date,
        voteAverage = vote_average
    )
}