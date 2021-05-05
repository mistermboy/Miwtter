package es.uniovi.miw.miwtter.database.domain

data class User(val name: String, val surname: String, val username: String, val password: String, var likes: List<Post> = mutableListOf<Post>()) {

    /**
     * The number of posts that the user has liked.
     */
    val numberOfLikes = this.likes.size
}
