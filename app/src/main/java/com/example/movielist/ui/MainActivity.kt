package com.example.movielist.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.R
import com.example.movielist.domain.model.MovieList
import com.example.movielist.ui.movie_list.MovieListViewModel
import com.example.movielist.ui.theme.MovieListTheme
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {

    private val movieListViewModel:MovieListViewModel by viewModel()
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: List<MovieList>
    private lateinit var adapter:AdopterClass

    private var dummyList: List<MovieList> = listOf(
        MovieList(true,"en","B","s","tru","12",2.9),
        MovieList(true,"en","B","s","tru","12",2.9),
        MovieList(true,"en","B","s","tru","12",2.9),
        MovieList(true,"en","B","s","tru","12",2.9),
        MovieList(true,"en","B","s","tru","12",2.9),
        MovieList(true,"en","B","s","tru","12",2.9),
        MovieList(true,"en","B","s","tru","12",2.9),
        MovieList(true,"en","B","s","tru","12",2.9),


        )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = AdopterClass(dummyList)
        recyclerView=findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        observer()

    }

    fun observer() {
        movieListViewModel.state.observe(this,{state->
            adapter.update(state.movies)
        })
    }


    }


