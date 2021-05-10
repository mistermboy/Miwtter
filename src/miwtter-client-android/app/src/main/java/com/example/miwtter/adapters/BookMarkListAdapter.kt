package com.danimeana.weatherapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.miwtter.R
import com.example.miwtter.ui.fav.PostsList
import com.like.LikeButton
import com.like.OnLikeListener
import es.uniovi.miw.miwtter.Miwtter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class BookMarkListAdapter(val items: List<Miwtter.FeedPost>) :
    RecyclerView.Adapter<BookMarkListAdapter.ViewHolder>() {

    class ViewHolder(val cardView: CardView) :
        RecyclerView.ViewHolder(cardView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.feed_bookmark, parent, false) as CardView


        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cardView.findViewById<TextView>(R.id.name_txt).text = items[position].ownerName+" @"+items[position].ownerUsername
        holder.cardView.findViewById<TextView>(R.id.post_text).text = items[position].content

        val post = Miwtter.FeedPost.newBuilder()
            .setOwnerName(items[position].ownerName)
            .setPostId(items[position].postId)
            .setContent(items[position].content)
            .setOwnerUsername(items[position].ownerUsername)
            .build()

        GlobalScope.async  {
            if(PostsList.requestFavPosts().contains(post)) {
                holder.cardView.findViewById<LikeButton>(R.id.star_button).isLiked = true
            }
        }


        holder.cardView.findViewById<LikeButton>(R.id.star_button).setOnLikeListener(object : OnLikeListener {
            override fun liked(likeButton: LikeButton) {
                GlobalScope.async  {  PostsList.createFavPost(post)}
            }
            override fun unLiked(likeButton: LikeButton) {
                 GlobalScope.async {
                     PostsList.removeFavPost(post)
                 }
            }
        })

    }

    override fun getItemCount() = items.size
}