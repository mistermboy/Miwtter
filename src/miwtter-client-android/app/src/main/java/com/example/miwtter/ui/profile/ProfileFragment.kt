package com.example.miwtter.ui.profile

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

class ProfileFragment : Fragment() {

    private lateinit var feedViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        feedViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)
        val view = inflater.inflate(R.layout.profile_fragment, container, false)
        return view
    }
}