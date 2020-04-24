package com.example.moviegram.ui

import com.example.moviegram.data.model.Movie

interface FavoriteClickListener {
    fun favoriteClick(movie: Movie)
}