package com.danimeana.weatherapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.miwtter.R

class FeedListAdapter(val items: List<Tweet>) :
    RecyclerView.Adapter<FeedListAdapter.ViewHolder>() {

    class ViewHolder(val cardView: CardView) :
        RecyclerView.ViewHolder(cardView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.feed_item, parent, false) as CardView
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    override fun getItemCount() = items.size
}