package com.example.moviegram.ui.main.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviegram.data.model.Movie
import com.example.moviegram.data.repositiry.MovieRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MovieRepository) : ViewModel() {


    private val _mMovies = MediatorLiveData<List<Movie>>()

    val movies: LiveData<List<Movie>> = _mMovies

    fun allMovies() = viewModelScope.launch {
        _mMovies.postValue(repository.getAllMovies())
    }

    fun updateMovies(movie: Movie) = viewModelScope.launch {
        repository.updateMovies(movie)
    }

}
