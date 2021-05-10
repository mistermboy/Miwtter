package es.uniovi.miw.miwtter.persistence

import es.uniovi.miw.miwtter.Miwtter

interface LocalDatabase {

    /**
     * Gets a list containing all the posts in the local database.
     */
    suspend fun getAll(): List<Miwtter.FeedPost>

    /**
     * Retrieves the corresponding post from the local database by its postId.
     *
     * @param postId is an string containing the id of the post to look for.
     * @return the post that matched the given id if any. Otherwise null.
     */
    suspend fun findByPostId(postId: String): Miwtter.FeedPost?

    /**
     * Adds the given post to the local database.
     *
     * @param post is a [Miwtter.FeedPost] that will be stored in the local database.
     * @return true if the insert operation was successful. False otherwise.
     */
    suspend fun addPost(post: Miwtter.FeedPost): Boolean

    /**
     * Removes a post from the local database based on its postId. If the post is not in the
     * dabase hould return null.
     *
     * @param postId is the id of the post to remove.
     * @return the post that was removed if it existed in the local database or null.
     */
    suspend fun removePost(postId: String): Miwtter.FeedPost?
}