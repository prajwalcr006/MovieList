package com.example.movielist.data.repository

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.example.cryptocurrencyapp.common.Resource
import com.example.movielist.data.remote.MovieListApi
import com.example.movielist.domain.model.MovieList
import com.example.movielist.domain.repository.MovieRepository
import java.io.IOException


import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieListApi: MovieListApi): MovieRepository {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getMovieList(): Resource<List<MovieList>> {

        return try {
            val rawList = movieListApi.getMovies().results

            Log.d("debugRepo",rawList[0].toString()?:"No message")

            val list = rawList.map { elements-> elements.toMovieList()}
            Resource.Success(list)
        } catch (e: HttpException) {
            Resource.Error(e.message?:"Unexpected error")
        } catch (e: IOException) {
            Resource.Error("Could not call api, check internet")
        }

    }
}