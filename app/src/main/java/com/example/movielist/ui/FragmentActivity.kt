package com.example.movielist.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movielist.R
import com.example.movielist.databinding.DetailFragmentBinding
import com.example.movielist.domain.model.MovieList
import com.example.movielist.ui.movie_list.MovieListViewModel
import com.example.movielist.common.constants.DEFAULT_IMAGE_URL
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL



class FragmentActivity: AppCompatActivity(),KoinComponent {

    private val airPlaneModeReceiver: AirPlaneModeReceiver by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(
            airPlaneModeReceiver,
            IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        )

        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(airPlaneModeReceiver)
    }

}



class DetailFragment: Fragment() {

    private lateinit var myParams: MovieList
    private lateinit var binding: DetailFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailFragmentBinding.inflate(inflater,container,false)
        val view = binding.root
        myParams = arguments?.getParcelable<MovieList>("movieList")!!
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.filmTitle.text = myParams.title
        binding.filmPoster.setImageBitmap(getBitmapFromURL(myParams.posterPath?: DEFAULT_IMAGE_URL))
        binding.filmDescription.text = myParams.overview
        binding.filmLanguage.text = getString(R.string.language_text, myParams.originalLanguage)
        binding.filmReleaseDate.text = getString(R.string.release_date, myParams.releaseDate)
        binding.filmVoteAvg.text = getString(R.string.vote_avg, myParams.voteAverage.toString())


    }

    private fun getBitmapFromURL(src: String?): Bitmap? {
        return try {
            Log.e("src", src!!)
            val url = URL(src)
            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input = connection.inputStream
            val myBitmap = BitmapFactory.decodeStream(input)
            Log.e("Bitmap", "returned")
            myBitmap
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("Exception", e.message!!)
            null
        }
    }
}

class ListFragment: Fragment(),AdopterClass.ItemClickListner {
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

class AirPlaneModeReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            val isTurnedOn = Settings.Global.getInt(
                context?.contentResolver,
                Settings.Global.AIRPLANE_MODE_ON
            )!=0

            if(isTurnedOn)
                Toast.makeText(context,"Airplane mode turned on!!!",Toast.LENGTH_LONG).show()
            else
                Toast.makeText(context,"Airplane mode turned of",Toast.LENGTH_LONG).show()
        }
    }

}

