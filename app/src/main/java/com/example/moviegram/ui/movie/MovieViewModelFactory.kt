package com.example.moviegram.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviegram.data.repositiry.MovieRepository
import com.example.moviegram.ui.main.viewmodel.MainViewModel

@Suppress("UNCHECKED_CAST")
class MovieViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(MovieViewModel::class.java)){
            MovieViewModel(this.repository) as T
        }
        else throw IllegalArgumentException("MainViewModel Not Found")
    }
}