package com.example.moviegram.ui.main.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviegram.data.model.MovieModel
import com.example.moviegram.data.repositiry.MovieRepository
import com.example.moviegram.ui.main.MainNavigator

class MainViewModel(private val repository: MovieRepository) : ViewModel() {

    private val movies: MutableLiveData<List<MovieModel>>  = MutableLiveData(loadUsers())


    init {
        loadUsers()
    }


    fun getMovies(): LiveData<List<MovieModel>>{
        return movies
    }

    private fun loadUsers() = repository.getAllMovies()



}
