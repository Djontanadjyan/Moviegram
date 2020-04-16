package com.example.moviegram.ui.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviegram.R

class MovieActivity: AppCompatActivity(){

    val MOVIE_KEY = "MOVIE_KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_activity)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.movie_container, MovieFragment.newInstance(intent.getBundleExtra(MOVIE_KEY)))
                .addToBackStack(null)
                .commit()
        }

    }

}