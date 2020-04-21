package com.example.moviegram.ui.main


import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviegram.R
import com.example.moviegram.data.model.MovieModel
import com.example.moviegram.data.repositiry.MovieRepository
import com.example.moviegram.ui.ShowAlertDialog
import com.example.moviegram.ui.main.adapter.BaseAdapter
import com.example.moviegram.ui.main.viewmodel.MainViewModel
import com.example.moviegram.ui.main.viewmodel.MainViewModelFactory
import com.example.moviegram.ui.movie.MovieActivity
import kotlinx.android.synthetic.main.dialog_out.*
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment(), MainNavigator, ShowAlertDialog {

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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("Fragment", "onCreateView ")
        return inflater.inflate(R.layout.main_fragment, container, false)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Fragment", "onCreate ")
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showAlertDialog()
            }
        })
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("Fragment", "onActivityCreated ")

        repository = MovieRepository()
        viewModelFactory =
            MainViewModelFactory(
                repository
            )
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        rvAdapter = BaseAdapter(viewModel.getMovies().value!!, this)

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            rv_main.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = rvAdapter
            }
        }
        else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            rv_main.apply {
                layoutManager = GridLayoutManager(activity, 2)
                adapter = rvAdapter
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
                Log.d(
                    "INTENT onActivityResult",
                    data?.getStringExtra("KEY_INTENT_RESULT").toString()
                )

            }
        }
    }

    override fun showAlertDialog() {
        val builder = AlertDialog.Builder(context, R.style.AlertDialogTheme)
        val inflater = activity?.layoutInflater
        builder.setView(inflater?.inflate(R.layout.dialog_out, null))
            .setPositiveButton(getString(R.string.exit_alerdialog_possitivebutton)) { _, _ ->
            activity?.finish()
             }
            .setNegativeButton(getString(R.string.exit_alerdialog_negativebutton)) { _, _ ->
            return@setNegativeButton
        }
        builder.create()
        builder.show()
    }
}

