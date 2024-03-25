package com.example.movielist.ui.fragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movielist.R
import com.example.movielist.common.constants
import com.example.movielist.databinding.DetailFragmentBinding
import com.example.movielist.domain.model.MovieList
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

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
        binding.filmPoster.setImageBitmap(getBitmapFromURL(myParams.posterPath?: constants.DEFAULT_IMAGE_URL))
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