package com.example.moviegram.ui.favorite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviegram.data.model.Movie
import com.example.moviegram.data.repositiry.MovieRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _mFavoriteMovies = MediatorLiveData<List<Movie>>()

    val favoritemovies: LiveData<List<Movie>> = _mFavoriteMovies

    fun allFavoriteMovies() = viewModelScope.launch {
        _mFavoriteMovies.postValue(repository.getAllFavoriteMovies())
    }

    fun updateMovies(movie: Movie) = viewModelScope.launch {
        repository.updateMovies(movie)
    }

}