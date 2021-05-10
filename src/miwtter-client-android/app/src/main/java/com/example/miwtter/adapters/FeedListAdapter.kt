package com.danimeana.weatherapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.miwtter.R
import com.example.miwtter.databinding.ActivityLoginBinding
import es.uniovi.miw.miwtter.Miwtter

class FeedListAdapter(val items: List<Miwtter.FeedPost>) :
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
        holder.cardView.findViewById<TextView>(R.id.name_txt).text = items[position].ownerName+" @"+items[position].ownerUsername
        holder.cardView.findViewById<TextView>(R.id.post_text).text = items[position].content
        holder.cardView.findViewById<TextView>(R.id.num_likes).text = ""+items[position].numberOfLikes
    }

    override fun getItemCount() = items.size
}