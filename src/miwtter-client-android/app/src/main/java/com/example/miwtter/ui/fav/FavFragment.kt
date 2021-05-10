package com.example.miwtter.ui.fav

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danimeana.weatherapp.FeedListAdapter
import com.danimeana.weatherapp.Tweet
import com.example.miwtter.R
import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.clients.FeedServiceClient
import es.uniovi.miw.miwtter.persistence.LocalDatabaseRoomImpl

class FavFragment : Fragment() {


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


        favViewModel.requestCreatePost(Miwtter.FeedPost.newBuilder().setContent("Esdto es un fav post").setPostId("222").build())
        favViewModel.requestFavPosts()
        val all = favViewModel.postsList.value

        favViewModel.postsList.observe(viewLifecycleOwner) { postsList: List<Miwtter.FeedPost> ->
            favList.adapter = FeedListAdapter(postsList)
        }
        return view
    }

}