package com.example.moviegram.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviegram.data.model.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg movies: Movie)

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM movies WHERE favorites =1")
    suspend fun getAllFavoritesMovies(): List<Movie>

    @Delete
    suspend fun delete(movie: Movie)

    @Update
    suspend fun update(movie: Movie)
}