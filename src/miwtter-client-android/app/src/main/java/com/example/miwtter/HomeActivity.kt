package com.example.miwtter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.danimeana.weatherapp.FeedListAdapter
import com.danimeana.weatherapp.Tweet
import com.example.miwtter.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController


class HomeActivity : AppCompatActivity() {

    private val items = listOf(
        Tweet("Lunes, 03 de mayo del 20201", "Lluvioso", 4f, 13f),
        Tweet("Martes, 04 de mayo del 20201", "Cubierto", 5f, 15f),
        Tweet("Miércoles, 05 de mayo del 20201", "Cubierto", 7f, 17f),
        Tweet("Jueves, 06 de mayo del 20201", "Lluvioso", 5f, 14f),
        Tweet("Jueves, 06 de mayo del 20201", "Lluvioso", 5f, 14f),
        Tweet("Jueves, 06 de mayo del 20201", "Lluvioso", 5f, 14f),
        Tweet("Jueves, 06 de mayo del 20201", "Lluvioso", 5f, 14f),
        Tweet("Jueves, 06 de mayo del 20201", "Lluvioso", 5f, 14f),
        Tweet("Jueves, 06 de mayo del 20201", "Lluvioso", 5f, 14f),
        Tweet("Jueves, 06 de mayo del 20201", "Lluvioso", 5f, 14f),
        Tweet("Jueves, 06 de mayo del 20201", "Lluvioso", 5f, 14f),
        Tweet("Jueves, 06 de mayo del 20201", "Lluvioso", 5f, 14f),
        Tweet("Jueves, 06 de mayo del 20201", "Lluvioso", 5f, 14f),
        Tweet("Viernes, 07 de mayo del 20201", "Despejado", 10f, 21f)
    )


    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
    }

    private fun initialize() {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_feed, R.id.navigation_fav, R.id.navigation_profile))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }



}