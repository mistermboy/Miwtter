package com.example.miwtter.ui.fav

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.persistence.LocalDatabaseRoomImpl
import kotlinx.coroutines.launch

class FavViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is fav Fragment"
    }
    val text: LiveData<String> = _text

    private val _postsList: MutableLiveData<List<Miwtter.FeedPost>> by lazy {
        MutableLiveData<List<Miwtter.FeedPost>>()
    }

    val postsList: LiveData<List<Miwtter.FeedPost>>
        get() = _postsList

    fun requestFavPosts(){
        viewModelScope.launch {
            _postsList.value = PostsList.requestFavPosts()
        }
    }

    fun requestCreatePost(feedPost:Miwtter.FeedPost) {
        viewModelScope.launch {
            PostsList.createFavPost(feedPost)
        }
    }


}

class PostsList() {
    companion object {
        suspend fun requestFavPosts(): List<Miwtter.FeedPost> {
            return LocalDatabaseRoomImpl.getAll()
        }

        suspend fun createFavPost(feedPost:Miwtter.FeedPost) {
            LocalDatabaseRoomImpl.addPost(feedPost)
        }

        suspend fun removeFavPost(feedPost:Miwtter.FeedPost) {
            LocalDatabaseRoomImpl.removePost(feedPost.postId)
        }
    }
}
