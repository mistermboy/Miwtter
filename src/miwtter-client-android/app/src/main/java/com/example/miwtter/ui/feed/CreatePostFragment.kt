package com.example.miwtter.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.miwtter.App
import com.example.miwtter.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.clients.PostServiceClient
import es.uniovi.miw.miwtter.persistence.Settings


class CreatePostFragment : Fragment() {


    private lateinit var feedViewModel: FeedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.create_post_fragment, container, false)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val postBtn = view.findViewById<FloatingActionButton>(R.id.create_post_btn)
        postBtn.setOnClickListener {
            val service = PostServiceClient
            val content = view.findViewById<TextView>(R.id.post_content_txt)
            val request = Miwtter.CreatePostRequest.newBuilder()
                .setActorUsername(Settings(App.instance).username)
                .setContent(content.text.toString())
                .build()

            val response = service.create(request)
            when (response.responseStatus) {
                Miwtter.CreatePostResponse.ResponseStatus.POST_CREATED -> {
                    content.text = ""
                    Toast.makeText(context, "Posteao", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return view
    }


}

