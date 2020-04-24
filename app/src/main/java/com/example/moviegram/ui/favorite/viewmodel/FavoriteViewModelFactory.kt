package com.example.moviegram.ui.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviegram.data.repositiry.MovieRepository
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class FavoriteViewModelFactory (private val repository : MovieRepository) :ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)){
            FavoriteViewModel(this.repository) as T
        }
        else throw IllegalArgumentException("FavoriteViewModel Not Found")
    }


}