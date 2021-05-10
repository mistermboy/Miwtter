package com.example.miwtter.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danimeana.weatherapp.FeedListAdapter
import com.danimeana.weatherapp.Tweet
import com.example.miwtter.R
import com.example.miwtter.ui.fav.FavFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.clients.FeedServiceClient
import es.uniovi.miw.miwtter.clients.PostServiceClient
import es.uniovi.miw.miwtter.clients.UsersServiceClient

class FeedFragment : Fragment() {

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


        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val service = FeedServiceClient()
        val s = PostServiceClient()
        val r = Miwtter.CreatePostRequest.newBuilder().setActorUsername("labra").setContent("y dame más alcooooholl mas medicina").build()
        s.create(r)
        val request = Miwtter.GetFeedRequest.newBuilder().
                setActorUsername("labra")
                .build()

        val response = service.getFeed(request)
        feedList.adapter = FeedListAdapter(response.postsList)


        view.findViewById<FloatingActionButton>(R.id.create_post_btn).setOnClickListener{
           val transaction = getFragmentManager()?.beginTransaction()
            transaction?.replace(R.id.nav_host_fragment,CreatePostFragment())
            transaction?.commit()
        }

        return view
    }
}