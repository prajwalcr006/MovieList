package com.example.movielist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.R
import com.example.movielist.domain.model.MovieList
import com.example.movielist.ui.AdopterClass
import com.example.movielist.ui.movie_list.MovieListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment: Fragment(), AdopterClass.ItemClickListner {
    private val movieListViewModel: MovieListViewModel by viewModel()
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: List<MovieList>
    private lateinit var adapter: AdopterClass
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_fragment,container,false)

        adapter = AdopterClass(emptyList(),this)
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

    override fun onItemClick(movieList: MovieList) {
        val bundle = Bundle()
        bundle.putParcelable("movieList",movieList)

        val detailFragment = DetailFragment()
        detailFragment.arguments = bundle



        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container,detailFragment)
            .addToBackStack(null)
            .commit()
    }
}