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
import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.clients.FeedServiceClient
import es.uniovi.miw.miwtter.clients.PostServiceClient
import es.uniovi.miw.miwtter.clients.UsersServiceClient

class FeedFragment : Fragment() {

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


        val service = FeedServiceClient()
        val request = Miwtter.GetFeedRequest.newBuilder().
                setActorUsername("labra")
                .build()

        val response = service.getFeed(request)
        feedList.adapter = FeedListAdapter(response.postsList)
        return view
    }
}