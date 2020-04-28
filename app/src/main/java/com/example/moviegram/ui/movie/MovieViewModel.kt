package com.example.moviegram.ui.movie

import androidx.lifecycle.*
import com.example.moviegram.data.model.Movie
import com.example.moviegram.data.repositiry.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) :ViewModel(){

    private val _mMovieId = MutableLiveData<Int>()

    val movie: LiveData<Movie> = Transformations
        .switchMap(_mMovieId){
            movie -> repository.getMovie(movie)
        }

    fun updateMovies(movie: Movie) = viewModelScope.launch {
        repository.updateMovies(movie)
    }

    fun setMovieId(movieId: Int?){

        val update = movieId

        _mMovieId.value = update
    }

}