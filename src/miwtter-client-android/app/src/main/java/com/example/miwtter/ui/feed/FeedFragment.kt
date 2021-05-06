package com.example.miwtter.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.danimeana.weatherapp.FeedListAdapter
import com.example.miwtter.R
import com.example.miwtter.databinding.ActivityHomeBinding

class FeedFragment : Fragment() {

    private lateinit var feedViewModel: FeedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        feedViewModel =
            ViewModelProvider(this).get(FeedViewModel::class.java)
        val root = inflater.inflate(R.layout.feed_fragment, container, false)

        //binding.feedList.layoutManager = LinearLayoutManager(this)
        //binding.feedList.adapter = FeedListAdapter(items)

        return root
    }
}