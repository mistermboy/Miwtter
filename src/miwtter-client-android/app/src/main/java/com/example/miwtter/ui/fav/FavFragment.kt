package com.example.miwtter.ui.fav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danimeana.weatherapp.BookMarkListAdapter
import com.example.miwtter.R
import es.uniovi.miw.miwtter.Miwtter

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


        favViewModel.requestFavPosts()
        favViewModel.postsList.observe(viewLifecycleOwner) { postsList: List<Miwtter.FeedPost> ->
            favList.adapter = BookMarkListAdapter(postsList)
        }
        return view
    }

}