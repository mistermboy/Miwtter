package com.example.miwtter.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danimeana.weatherapp.FeedListAdapter
import com.danimeana.weatherapp.Tweet
import com.example.miwtter.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.clients.FeedServiceClient
import es.uniovi.miw.miwtter.clients.PostServiceClient
import es.uniovi.miw.miwtter.clients.UsersServiceClient

class CreatePostFragment : Fragment() {


    private lateinit var feedViewModel: FeedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        feedViewModel =
            ViewModelProvider(this).get(FeedViewModel::class.java)
        val view = inflater.inflate(R.layout.feed_fragment, container, false)
        val feedList: RecyclerView = view.findViewById(R.id.feedList)
        feedList.layoutManager = LinearLayoutManager(activity)
        return view
    }
}