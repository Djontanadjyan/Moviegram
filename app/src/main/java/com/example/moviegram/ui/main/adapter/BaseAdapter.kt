package com.example.moviegram.ui.main.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.moviegram.R
import com.example.moviegram.data.model.Movie
import com.example.moviegram.ui.FavoriteClickListener
import com.example.moviegram.ui.MainNavigator
import com.example.moviegram.ui.SnackHandler
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item.view.*
import java.util.*

class BaseAdapter(
    val onItemClick: MainNavigator,
    val onFavoriteClick: FavoriteClickListener
) :
    RecyclerView.Adapter<BaseAdapter.ViewHolder>() {

    private var movieList: ArrayList<Movie> = arrayListOf()
    private val FADE_DURATION: Long = 1000
    private val lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie: Movie = movieList[position]
        holder.bind(movie)

        setAnimation(holder.itemView, position)
        if (movie.enabled) {
            holder.itemView.favorite_button.setBackgroundResource(R.drawable.ic_favorite_24dp)
        } else holder.itemView.favorite_button.setBackgroundResource(R.drawable.ic_favorite_border_24dp)

    }

    fun setMovies(movies: List<Movie>) {
        movieList.clear()
        movieList.addAll(movies)
        notifyDataSetChanged()
    }

    private fun setAnimation(v: View, position: Int) {
        if (position > lastPosition) {
            val animation = ScaleAnimation(
                0.0f,
                1.0f,
                0.0f,
                1.0f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
            animation.duration = FADE_DURATION
            v.startAnimation(animation)
            lastPosition == position
        }
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  , SnackHandler {
        fun bind(movie: Movie) {
            itemView.txtView_title.text = movie.title
            itemView.imgView_icon.load(movie.poster_url)
            Log.d("bind", "bind " + movie.id.toString() + movie.enabled.toString())
            itemView.detail_button.setOnClickListener {
                onItemClick.onItemClick(movie)
                itemView.txtView_title.setTextColor(Color.parseColor("#ff0000"))
            }
            itemView.favorite_button.setOnClickListener {
                if (!movie.enabled) {
                    showSnackbar(movie)
                    itemView.favorite_button.setBackgroundResource(R.drawable.ic_favorite_24dp)
                } else {
                    onFavoriteClick.favoriteClick(movie)
                    itemView.favorite_button.setBackgroundResource(R.drawable.ic_favorite_border_24dp)
                }
            }
        }

        override fun showSnackbar(movie: Movie) {
            Snackbar.make(itemView, R.string.snackBar_text, Snackbar.LENGTH_LONG)
                .setAction(R.string.snackbar_actinon) {
                    itemView.favorite_button.setBackgroundResource(R.drawable.ic_favorite_border_24dp)
                    onFavoriteClick.favoriteClick(movie)
                }
                .show()
            onFavoriteClick.favoriteClick(movie)
        }

    }
}