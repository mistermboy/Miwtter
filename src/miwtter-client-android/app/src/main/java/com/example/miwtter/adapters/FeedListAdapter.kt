package com.danimeana.weatherapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.miwtter.App
import com.example.miwtter.R
import com.example.miwtter.ui.fav.FavViewModel
import com.example.miwtter.ui.fav.PostsList
import com.like.LikeButton
import com.like.OnLikeListener
import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.clients.PostServiceClient
import es.uniovi.miw.miwtter.clients.UsersServiceClient
import es.uniovi.miw.miwtter.persistence.Settings
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

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

        val userRequest  = Miwtter.FindUserRequest.newBuilder()
            .setUsername(Settings(App.instance).username)
            .setFindPolicy(Miwtter.FindUserRequest.FindPolicy.AND)
            .build()


        val userClient = UsersServiceClient
        val response = userClient.find(userRequest)
        var posts  =response.userList.get(0).likedPostsList
        posts = posts.filter {
            p-> p.postId.contentEquals(post.postId)
        }
        if(!posts.isEmpty()) {
            holder.cardView.findViewById<LikeButton>(R.id.like_button).isLiked = true
            //holder.cardView.findViewById<TextView>(R.id.num_likes).text = Integer.toString(items[position].numberOfLikes+1)
        }

        holder.cardView.findViewById<LikeButton>(R.id.like_button).setOnLikeListener(object : OnLikeListener {
            override fun liked(likeButton: LikeButton) {
                val service = PostServiceClient
                val request = Miwtter.LikePostRequest.newBuilder()
                    .setActorUsername(Settings(App.instance).username)
                    .setPostId(items[position].postId)
                    .build()
                service.addLike(request)
                var numLikes = holder.cardView.findViewById<TextView>(R.id.num_likes).text
                holder.cardView.findViewById<TextView>(R.id.num_likes).text = Integer.toString(Integer.parseInt(numLikes.toString()) +1)
            }
            override fun unLiked(likeButton: LikeButton) {
                val service = PostServiceClient
                val request = Miwtter.RemoveLikeRequest.newBuilder()
                    .setActorUsername(Settings(App.instance).username)
                    .setPostId(items[position].postId)
                    .build()
                service.removeLike(request)
                var numLikes = holder.cardView.findViewById<TextView>(R.id.num_likes).text
                holder.cardView.findViewById<TextView>(R.id.num_likes).text = Integer.toString(Integer.parseInt(numLikes.toString()) -1)
            }
        })

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