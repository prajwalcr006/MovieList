package com.example.movielist.ui


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.movielist.R


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
        val intent = Intent(this,FragmentActivity::class.java)
        startActivity(intent)


    }
}


