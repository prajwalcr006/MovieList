package com.example.movielist.data.remote

import com.example.movielist.data.remote.dto.MovieListDto
import retrofit2.http.GET

interface MovieListApi {
    @GET("/3/movie/popular?api_key=6428a1052e81d1c43b8971f016a61343")
    suspend fun getMovies():MovieListDto
}