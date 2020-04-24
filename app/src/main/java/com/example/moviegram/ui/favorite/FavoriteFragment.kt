package com.example.moviegram.ui.favorite

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviegram.R
import com.example.moviegram.data.model.Movie
import com.example.moviegram.data.repositiry.MovieRepository
import com.example.moviegram.ui.FavoriteClickListener
import com.example.moviegram.ui.decorator.MovieItemDecorator
import com.example.moviegram.ui.favorite.viewmodel.FavoriteViewModel
import com.example.moviegram.ui.favorite.viewmodel.FavoriteViewModelFactory
import com.example.moviegram.ui.MainNavigator
import com.example.moviegram.ui.favorite.adapter.FavoriteAdapter
import com.example.moviegram.ui.main.adapter.BaseAdapter
import com.example.moviegram.ui.movie.MovieActivity
import kotlinx.android.synthetic.main.favotie_fragment.*

class FavoriteFragment : Fragment(), MainNavigator, FavoriteClickListener {

    val MOVIE_KEY = "MOVIE_KEY"
    private val SECOND_ACTIVITY_REQUEST_CODE = 0

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var repository: MovieRepository
    private lateinit var favoriteviewModelFactory: FavoriteViewModelFactory
    private var rvAdapter: FavoriteAdapter = FavoriteAdapter(this, this)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favotie_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("bind favorite", "onActivityCreated ")

        repository = MovieRepository(context!!)
        favoriteviewModelFactory = FavoriteViewModelFactory(repository)
        initFavoriteViewModel()

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            rv_favorite.apply {
                layoutManager = LinearLayoutManager(activity)
                addItemDecoration(MovieItemDecorator(context, 8, 8))
                adapter = rvAdapter

            }
        } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rv_favorite.apply {
                layoutManager = GridLayoutManager(activity, 2)
                adapter = rvAdapter
            }
        }
    }

    private fun initFavoriteViewModel() {
        favoriteViewModel  = ViewModelProvider(this, favoriteviewModelFactory).get(FavoriteViewModel::class.java)
        favoriteViewModel.allFavoriteMovies()

        favoriteViewModel.favoritemovies.observe(
            viewLifecycleOwner, Observer { favoriteMovies -> rvAdapter.setMovies(favoriteMovies)
            }
        )
    }

    override fun onItemClick(movie: Movie) {
        Toast.makeText(context, movie.id.toString(), Toast.LENGTH_LONG).show()
        val intent = Intent(context, MovieActivity::class.java)
        val args: Bundle = Bundle()
        args.putParcelable(MOVIE_KEY, movie)
        intent.putExtra(MOVIE_KEY, args)
        startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE)
    }

    override fun favoriteClick(movie: Movie) {
       movie.enabled = !movie.enabled
        favoriteViewModel.updateMovies(movie)
    }
}


