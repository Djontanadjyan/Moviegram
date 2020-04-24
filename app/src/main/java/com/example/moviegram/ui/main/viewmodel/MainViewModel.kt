package com.example.moviegram.ui.main.viewmodel


import android.app.Application
import android.view.View
import androidx.lifecycle.*
import com.example.moviegram.data.model.Movie
import com.example.moviegram.data.repositiry.MovieRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MovieRepository) : ViewModel() {



    private val _mMovies = MediatorLiveData<List<Movie>>()

    val movies : LiveData<List<Movie>> = _mMovies

    fun allMovies() = viewModelScope.launch {
        _mMovies.postValue(repository.getAllMovies())
    }

    fun updateMovies(movie: Movie) = viewModelScope.launch {
        repository.updateMovies(movie)
    }

}
