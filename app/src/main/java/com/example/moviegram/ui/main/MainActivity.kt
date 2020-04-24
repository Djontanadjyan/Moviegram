package com.example.moviegram.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.moviegram.R
import com.example.moviegram.ui.favorite.FavoriteFragment
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.bottom_navigation_bar.*
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_fragment.*

class MainActivity : AppCompatActivity() {

    private val mOnBottomNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener{item ->
        when(item.itemId){
         R.id.navigation_home -> {
             val fragment = MainFragment.Companion.newInstance()
             addFragment(fragment)

             return@OnNavigationItemSelectedListener true
         }
         R.id.navigation_favorite -> {
             val fragment = FavoriteFragment.newInstance()
             addFragment(fragment)
             return@OnNavigationItemSelectedListener  true
         }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, MainFragment.newInstance())
                    .commitNow()


        }

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme)
        } else {
            setTheme(R.style.LightTheme)
        }
        navigation.setOnNavigationItemSelectedListener (mOnBottomNavigationItemSelectedListener)
    }

    private fun addFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.main_container, fragment, fragment.javaClass.simpleName)
            .commit()
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return  true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_dark_theme -> {
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}
