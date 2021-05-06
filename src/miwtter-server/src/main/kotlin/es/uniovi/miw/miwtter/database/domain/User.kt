package es.uniovi.miw.miwtter.database.domain

import java.util.*

data class User(val name: String, val surname: String, val username: String, val password: String, var likedPosts: List<Post> = Collections.emptyList()) {

    /**
     * The number of posts that the user has liked.
     */
    val numberOfLikedPosts = this.likedPosts.size
}
