package com.example.moviegram.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviegram.data.model.Movie

@Database(entities = arrayOf(Movie::class), version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        var instance: MovieDatabase? = null

       fun getMovieDatabase(context: Context): MovieDatabase? {
            if (instance == null) {
                synchronized(MovieDatabase::class.java) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java,
                        "movie_db"
                    )
                        .build()
                }
            }
            return instance
        }
    }

}
