package com.example.moviegram.data.repositiry

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.moviegram.data.database.MovieDao
import com.example.moviegram.data.database.MovieDatabase
import com.example.moviegram.data.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class MovieRepository(context: Context): CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var movieDao: MovieDao? = null

    init {
        val db = MovieDatabase.getMovieDatabase(context)
        movieDao=db?.movieDao()
        insertMovies()
        }


    suspend fun getAllMovies(): List<Movie>? = movieDao?.getAllMovies()

    suspend fun getAllFavoriteMovies(): List<Movie>? = movieDao?.getAllFavoritesMovies()

    private suspend fun insertAllMoviesBG() {
        withContext(Dispatchers.IO) {
            movieDao?.insertAll(Movie( null, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
            movieDao?.insertAll(Movie( null,"Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
            movieDao?.insertAll(Movie( null,"Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
            movieDao?.insertAll(Movie(null, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
            movieDao?.insertAll(Movie(null, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
            movieDao?.insertAll(Movie(null, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
            movieDao?.insertAll(Movie(null, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
            movieDao?.insertAll(Movie(null, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
            movieDao?.insertAll(Movie(null, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
            movieDao?.insertAll(Movie(null, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
            movieDao?.insertAll(Movie(null, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
            movieDao?.insertAll(Movie(null, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
            movieDao?.insertAll(Movie(null, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
        }
    }

    private fun insertMovies(){
        launch {
            insertAllMoviesBG()
        }
    }

    suspend fun updateMovies(movie: Movie){
        movieDao?.update(movie)
        Log.d("update", movieDao?.update(movie).toString())
    }

//    private fun getMovies(){
//
//        movieDao?.insertAll(Movie(1, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
//        movieDao?.insertAll(Movie(2, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
//        movieDao?.insertAll(Movie(3, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
//        movieDao?.insertAll(Movie(4, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
//        movieDao?.insertAll(Movie(5, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
//        movieDao?.insertAll(Movie(6, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
//        movieDao?.insertAll(Movie(7, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
//        movieDao?.insertAll(Movie(8, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
//        movieDao?.insertAll(Movie(9, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
//        movieDao?.insertAll(Movie(10, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
//        movieDao?.insertAll(Movie(11, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
//        movieDao?.insertAll(Movie(12, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
//        movieDao?.insertAll(Movie(13, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
//        movieDao?.insertAll(Movie(14, "Pulp Fiction", "https://i.pinimg.com/564x/2e/71/53/2e71533dea72724dc97245d95632e0bf.jpg", "Kill Bill descripyion"))
//
//    }

}

