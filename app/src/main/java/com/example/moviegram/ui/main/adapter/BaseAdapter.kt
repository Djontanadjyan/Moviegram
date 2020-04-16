package com.example.moviegram.ui.main.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.moviegram.R
import com.example.moviegram.data.model.MovieModel
import com.example.moviegram.ui.main.MainNavigator
import kotlinx.android.synthetic.main.item.view.*

class BaseAdapter(private val movieList: List<MovieModel> , val onItemClick : MainNavigator) : RecyclerView.Adapter<BaseAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val movie: MovieModel = movieList[position]
            holder.bind(movie)
    }


 inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieModel: MovieModel) {
            itemView.txtView_title.text = movieModel.title
            itemView.imgView_icon.load(movieModel.poster_url)
            itemView.button.setOnClickListener {
                onItemClick.onItemClick(movieModel)
                movieModel.enabled = !movieModel.enabled
                itemView.txtView_title.setTextColor(Color.parseColor("#ff0000"))
            }
            if (movieModel.enabled) {
                itemView.txtView_title.setTextColor(Color.parseColor("#ff0000"))
            }
        }
    }

}