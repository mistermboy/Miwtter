package com.example.miwtter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.danimeana.weatherapp.FeedListAdapter
import com.danimeana.weatherapp.Tweet
import com.example.miwtter.databinding.ActivityFeedBinding

class FeedActivity : AppCompatActivity() {

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


    private lateinit var binding: ActivityFeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
    }

    private fun initialize() {
        binding.feedList.layoutManager = LinearLayoutManager(this)
        binding.feedList.adapter = FeedListAdapter(items)
    }



}