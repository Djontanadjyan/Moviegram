package com.example.moviegram.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviegram.data.repositiry.MovieRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return if(modelClass.isAssignableFrom(MainViewModel::class.java)){
           MainViewModel(this.repository) as T
       }
        else throw IllegalArgumentException("MainViewModel Not Found")
    }

}