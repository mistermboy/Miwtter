package com.example.miwtter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.danimeana.weatherapp.FeedListAdapter
import com.danimeana.weatherapp.Tweet
import com.example.miwtter.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.miwtter.ui.feed.CreatePostFragment
import com.example.miwtter.ui.feed.FeedFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton


class HomeActivity : AppCompatActivity(){

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
/*
        supportFragmentManager
                .beginTransaction()
                .add(binding.navHostFragment.id, FirstFragment())
                .commit()*/

    }

    private fun initialize() {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_feed, R.id.navigation_fav, R.id.navigation_profile))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

/*
        val createPostBtn: FloatingActionButton = findViewById( R.id.create_post_btn)
        createPostBtn.setOnClickListener {
            //navController.navigate()
        }
*/


    }

}