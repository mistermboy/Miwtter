package es.uniovi.miw.miwtter.database.domain

data class Post(val id: String, val content: String, val ownerUsername: String, var likes:List<User> = mutableListOf<User>()) {

    /**
     * The number of likes that the post has.
     */
    val numberOfLikes = this.likes.size
}
