package com.example.miwtter.ui.fav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danimeana.weatherapp.FeedListAdapter
import com.danimeana.weatherapp.Tweet
import com.example.miwtter.R

class FavFragment : Fragment() {

    private val items = listOf(
        Tweet("Lunes, 03 de mayo del 20201", "Lluvioso", 4f, 13f),
        Tweet("Martes, 04 de mayo del 20201", "Cubierto", 5f, 15f),
        Tweet("Miércoles, 05 de mayo del 20201", "Cubierto", 7f, 17f),
    )

    private lateinit var favViewModel: FavViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favViewModel =
            ViewModelProvider(this).get(FavViewModel::class.java)
        val view = inflater.inflate(R.layout.fav_fragment, container, false)
        val favList: RecyclerView = view.findViewById(R.id.favList)
        favList.layoutManager = LinearLayoutManager(activity)
        favList.adapter = FeedListAdapter(items)
        return view
    }
}