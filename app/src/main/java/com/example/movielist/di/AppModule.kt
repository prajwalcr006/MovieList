package com.example.movielist.di

import com.example.movielist.common.constants
import com.example.movielist.data.remote.MovieListApi
import com.example.movielist.data.repository.MovieRepositoryImpl
import com.example.movielist.domain.repository.MovieRepository
import com.example.movielist.domain.use_case.GetMoviesUseCase
import com.example.movielist.ui.AirPlaneModeReceiver
import com.example.movielist.ui.movie_list.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
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

    single<MovieRepository> {
        MovieRepositoryImpl(get())
    }

    single {
        AirPlaneModeReceiver()
    }


    factory {
        GetMoviesUseCase(get())
    }


    viewModel {
        MovieListViewModel(get())
    }

}