package com.danimeana.weatherapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.miwtter.R
import com.like.LikeButton
import com.like.OnLikeListener
import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.clients.PostServiceClient

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
        holder.cardView.findViewById<TextView>(R.id.num_likes).text = items[position].numberOfLikes.toString()

        holder.cardView.findViewById<LikeButton>(R.id.like_button).setOnLikeListener(object : OnLikeListener {
            override fun liked(likeButton: LikeButton) {
                val service = PostServiceClient
                val request = Miwtter.LikePostRequest.newBuilder()
                    .setActorUsername("labra")
                    .setPostId(items[position].postId)
                    .build()
                service.addLike(request)
                holder.cardView.findViewById<TextView>(R.id.num_likes).text = Integer.toString(items[position].numberOfLikes+1)
            }
            override fun unLiked(likeButton: LikeButton) {
                val service = PostServiceClient
                val request = Miwtter.RemoveLikeRequest.newBuilder()
                    .setActorUsername("labra")
                    .setPostId(items[position].postId)
                    .build()
                service.removeLike(request)
                holder.cardView.findViewById<TextView>(R.id.num_likes).text = Integer.toString(items[position].numberOfLikes)
            }
        })

    }

    override fun getItemCount() = items.size
}