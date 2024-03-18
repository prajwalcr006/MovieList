package com.example.movielist.domain.model

data class MovieList(
    val adult: Boolean,
    val originalLanguage: String,
    val posterPath: String,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val voteAverage: Double
)
