package com.example.movielist.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movielist.R
import com.example.movielist.ui.movie_list.MovieListViewModel
import com.example.movielist.ui.fragments.ListFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject



class FragmentActivity: AppCompatActivity(),KoinComponent {

    private val airPlaneModeReceiver: AirPlaneModeReceiver by inject()
    private val movieListViewModel: MovieListViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(
            airPlaneModeReceiver,
            IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        )

        observeLd()

        setContentView(R.layout.activity_main)
    }

    private fun observeLd() {
        movieListViewModel.state.observe(this) {state->

            if(state.error.isNotEmpty()) {
                Toast.makeText(this,state.error,Toast.LENGTH_LONG).show()
            }
            else
                if(!state.isLoading)
                {
                    val listFragment = ListFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,listFragment)
                        .commit()
                }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(airPlaneModeReceiver)
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

