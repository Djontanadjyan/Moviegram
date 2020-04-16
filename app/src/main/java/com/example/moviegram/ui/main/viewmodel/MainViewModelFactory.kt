package com.example.moviegram.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviegram.data.repositiry.MovieRepository

class MainViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return if(modelClass.isAssignableFrom(MainViewModel::class.java)){
           MainViewModel(this.repository) as T
       }
        else throw IllegalArgumentException("ViewModel Not Found")
    }

}