package com.example.moviegram.ui.favorite.adapter

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
import com.example.moviegram.ui.main.adapter.BaseAdapter
import kotlinx.android.synthetic.main.item.view.*
import java.util.*

class FavoriteAdapter(val onItemClick: MainNavigator, val onFavoriteClick: FavoriteClickListener) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private var movieList: ArrayList<Movie> = arrayListOf()
    private val FADE_DURATION: Long = 1000
    private val lastPosition = -1
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun getItemCount(): Int = movieList.size


    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {

        val movie = movieList[position]
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


    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
                    onFavoriteClick.favoriteClick(movie)
                    itemView.favorite_button.setBackgroundResource(R.drawable.ic_favorite_24dp)
                } else {
                    onFavoriteClick.favoriteClick(movie)
                    movieList.remove(movie)
                    notifyItemRemoved(adapterPosition);
                    itemView.favorite_button.setBackgroundResource(R.drawable.ic_favorite_border_24dp)
                }
            }
        }
    }


}

