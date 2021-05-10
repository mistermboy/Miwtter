package es.uniovi.miw.miwtter.database.inmemory

data class InMemoryDatabaseUserDataClass(val name: String, val surname: String, val username: String, val password: String, var likedPosts: MutableList<InMemoryDatabasePostDataClass> = mutableListOf()) {

    /**
     * The number of posts that the user has liked.
     */
    val numberOfLikedPosts = this.likedPosts.size
}
