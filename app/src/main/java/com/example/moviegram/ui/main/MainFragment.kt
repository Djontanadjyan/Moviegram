package com.example.moviegram.ui.main


import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviegram.R
import com.example.moviegram.data.model.MovieModel
import com.example.moviegram.data.repositiry.MovieRepository
import com.example.moviegram.ui.main.adapter.BaseAdapter
import com.example.moviegram.ui.main.viewmodel.MainViewModel
import com.example.moviegram.ui.main.viewmodel.MainViewModelFactory
import com.example.moviegram.ui.movie.MovieActivity
import com.example.moviegram.ui.movie.MovieFragment
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment() , MainNavigator {

    private val SECOND_ACTIVITY_REQUEST_CODE = 0
    val MOVIE_KEY = "MOVIE_KEY"

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var repository: MovieRepository
    private lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var rvAdapter: BaseAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)

    }



    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rv_main.apply {
                layoutManager = GridLayoutManager(activity, 2)
                adapter = rvAdapter
            }
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            rv_main.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = rvAdapter
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        repository = MovieRepository()
        viewModelFactory =
            MainViewModelFactory(
                repository
            )
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        rvAdapter = BaseAdapter(viewModel.getMovies().value!!, this)

        rv_main.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = rvAdapter
                }

        fab_main.setOnClickListener {
            val textImplicit = "Send to Friend"
            val intentImplicit = Intent()
            intentImplicit.action = Intent.ACTION_SEND
            intentImplicit.putExtra(Intent.EXTRA_TEXT, textImplicit)
            intentImplicit.type = "text/plain"
            val chooser = Intent.createChooser(intentImplicit, getString(R.string.implicit_text))

            if (intentImplicit.resolveActivity(activity!!.packageManager) != null){
                startActivity(chooser)
            }
        }
    }

    override fun onItemClick(movie: MovieModel) {
        Toast.makeText(context, movie.id.toString(), Toast.LENGTH_LONG).show()
        val intent = Intent(context, MovieActivity::class.java)
        val args: Bundle = Bundle()
        args.putParcelable(MOVIE_KEY, movie)
        intent.putExtra(MOVIE_KEY, args)
        Log.d("INTENT MainFragment", args.toString())
        startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Log.d("INTENT onActivityResult", data?.getStringExtra("KEY_INTENT_RESULT").toString())

            }
        }
    }
}

