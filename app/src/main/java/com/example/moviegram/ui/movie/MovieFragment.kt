package com.example.moviegram.ui.movie

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import coil.api.load
import com.example.moviegram.R
import com.example.moviegram.data.model.MovieModel
import kotlinx.android.synthetic.main.movie_fragment.*

class MovieFragment :Fragment() {

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                intentToBack()
            }
        })

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        movie_txt_description.text = arguments?.getParcelable<MovieModel>(MOVIE_KEY)?.description
        movie_image.load(arguments?.getParcelable<MovieModel>(MOVIE_KEY)?.poster_url)



    }

    private fun intentToBack(){
        editText = movie_txt_edittext.text.toString()
        checkBox = movie_checkbox.isChecked
        val intent = Intent()
        intent.putExtra("KEY_INTENT_RESULT", editText + checkBox.toString())
        activity?.setResult(Activity.RESULT_OK, intent)
        activity?.finish()
    }

}