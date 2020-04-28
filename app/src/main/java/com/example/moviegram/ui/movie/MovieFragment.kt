package com.example.moviegram.ui.movie

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.example.moviegram.R
import com.example.moviegram.data.model.Movie
import com.example.moviegram.data.repositiry.MovieRepository
import com.example.moviegram.ui.FavoriteClickListener
import kotlinx.android.synthetic.main.movie_fragment.*

@Suppress("PLUGIN_WARNING")
class MovieFragment : Fragment(), FavoriteClickListener {

    val MOVIE_KEY = "MOVIE_KEY"

    var checkBox: Boolean? = null
    var editText: String? = null


    companion object {
        fun newInstance(args: Bundle?): MovieFragment {
            val movieFragment = MovieFragment()
            movieFragment.arguments = args
            return movieFragment
        }
    }

    private lateinit var viewModel: MovieViewModel
    private lateinit var repository: MovieRepository
    private lateinit var viewModelFactory: MovieViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                intentToBack()
            }
        })

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        repository = MovieRepository(context!!)
        viewModelFactory = MovieViewModelFactory(repository)

        initViewModel()

        movie_fab.setOnClickListener {

            val textImplicit = "Send to Friend"
            val intentImplicit = Intent()
            intentImplicit.action = Intent.ACTION_SEND
            intentImplicit.putExtra(Intent.EXTRA_TEXT, textImplicit)
            intentImplicit.type = "text/plain"
            val chooser = Intent.createChooser(intentImplicit, getString(R.string.implicit_text))

            if (intentImplicit.resolveActivity(activity!!.packageManager) != null) {
                startActivity(chooser)
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieViewModel::class.java)

        viewModel.setMovieId(arguments?.getParcelable<Movie>(MOVIE_KEY)?.id)


        viewModel.movie.observe(viewLifecycleOwner, Observer {
            movie_collapsing.title = it.title
            movie_txt_description.text = it.description
            movie_image.load(it.poster_url)
            movie_checkbox.isChecked = it.enabled

            movie_checkbox.setOnCheckedChangeListener { _, _ -> favoriteClick(it) }
        })

    }

    private fun intentToBack() {
        val intent = Intent()
        intent.putExtra("KEY_INTENT_RESULT", checkBox.toString())
        activity?.setResult(Activity.RESULT_OK, intent)
        activity?.finish()
    }

    override fun favoriteClick(movie: Movie) {
        movie.enabled = !movie.enabled
        viewModel.updateMovies(movie)
    }

}