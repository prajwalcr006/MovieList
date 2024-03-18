package com.example.movielist.di

import com.example.movielist.common.constants
import com.example.movielist.data.remote.MovieListApi
import com.example.movielist.data.repository.MovieRepositoryImpl
import com.example.movielist.domain.repository.MovieRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieListApi::class.java)
    }

    factory<MovieRepository> {
        MovieRepositoryImpl(get())
    }

}