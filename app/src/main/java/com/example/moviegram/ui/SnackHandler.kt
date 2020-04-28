package com.example.moviegram.ui

import android.view.View
import com.example.moviegram.data.model.Movie

interface SnackHandler{
   fun showSnackbar(movie: Movie)

}