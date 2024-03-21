package com.example.movielist.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.R
import com.example.movielist.domain.model.MovieList
import com.example.movielist.ui.movie_list.MovieListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContentView(R.layout.activity_main)
    }


}

class DetailFragment: Fragment() {

}

class ListFragment: Fragment() {
    private val movieListViewModel:MovieListViewModel by viewModel()
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: List<MovieList>
    private lateinit var adapter:AdopterClass
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_fragment,container,false)
        recyclerView = view.findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
    }

    fun observe() {
        movieListViewModel.state.observe(viewLifecycleOwner,{state->
            adapter.update(state.movies)
        })
    }
}