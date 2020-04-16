package com.example.moviegram.data.repositiry

import android.util.Log
import com.example.moviegram.data.model.MovieModel

class MovieRepository {

    fun getAllMovies():List<MovieModel>{

        val movieList = arrayListOf<MovieModel>()
        movieList.add(MovieModel(1, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
        movieList.add(MovieModel(2, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
        movieList.add(MovieModel(3, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
        movieList.add(MovieModel(4, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
        movieList.add(MovieModel(5, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
        movieList.add(MovieModel(6, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
        movieList.add(MovieModel(7, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
        movieList.add(MovieModel(8, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
        movieList.add(MovieModel(9, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
        movieList.add(MovieModel(10, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
        movieList.add(MovieModel(11, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
        movieList.add(MovieModel(12, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
        movieList.add(MovieModel(13, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
        movieList.add(MovieModel(14, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))


        Log.d("MovieRepository" , movieList.size.toString())

        return movieList
    }



}