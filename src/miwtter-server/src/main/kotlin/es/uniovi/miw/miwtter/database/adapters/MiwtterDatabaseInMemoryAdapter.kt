package es.uniovi.miw.miwtter.database.adapters

import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.database.MiwtterDatabase
import es.uniovi.miw.miwtter.database.domain.Post
import es.uniovi.miw.miwtter.database.domain.User
import mu.KotlinLogging
import java.util.*

/**
 * This implementation of the Miwtter database persists the data in memory. Is very much like
 * a redis database. But customize for the Mwitter use case. It uses two in-memory data structures
 * to store the users and the posts.
 *
 * @version v1.0
 * @since v1.0
 */
object MiwtterDatabaseInMemoryAdapter : MiwtterDatabase {

    private val logger = KotlinLogging.logger(this.javaClass.canonicalName)

    // Initialized in the constructor and declared as variables to allow re-assignment.
    private var users: List<User>
    private var posts: List<Post>

    /**
     * This object stores the users and the posts in memory by means of two lists.
     *
     * @constructor Creates a MiwtterDatabaseInMemoryAdapter with empty collections for the users and the posts.
     */
    init {
        logger.info("Local database created.")
        users = Collections.emptyList()
        posts = Collections.emptyList()
    }

    /**
     * Removes all the users and posts from the database by assigning a new empty list to each data structure.
     */
    internal fun clean() {
        logger.warn("Cleaning the users list.")
        users = Collections.emptyList()

        logger.warn("Cleaning the posts list.")
        posts = Collections.emptyList()
    }

    /**
     * Iterates over each element of the users collection and finds the one that has the given username.
     * If none of the users has the given username then it returns null.
     *
     * @param username is the username to look for in the collection of users.
     * @return the user that match the given username. Null if no user matched the given username.
     */
    private fun findUser(username: String): User? {
        logger.info { "Looking for user [$username]." }
        val foundUser = this.users.find {
            user -> user.username.contentEquals(username)
        }
        if(foundUser == null) {
            logger.info { "User [$username] not found" }
        } else {
            logger.info { "User [$username] found" }
        }

        return foundUser
    }

    /**
     * Iterates over each element of the posts collection and finds the one that has the given postId.
     * If none of the posts has the given postId then it returns null.
     *
     * @param postId is the username to look for in the collection of users.
     * @return the post that match the given postId. Null if no post matched the given postId.
     */
    private fun findPost(postId: String): Post? {
        logger.info { "Looking for post [$postId]" }
        val foundPost = this.posts.find {
                post -> post.id.contentEquals(postId)
        }
        if(foundPost == null) {
            logger.info { "User [$postId] not found" }
        } else {
            logger.info { "User [$postId] found" }
        }

        return foundPost
    }

    override fun registerUser(request: Miwtter.RegisterUserRequest): Miwtter.RegisterUserResponse {
        if(this.findUser(request.username) != null) {
            logger.info { "The user [${request.username}] cannot be registered. It already exists in the database." }
            return Miwtter.RegisterUserResponse.newBuilder()
                .setResponseStatus(Miwtter.RegisterUserResponse.ResponseStatus.USERNAME_ALREADY_EXISTS)
                .build()
        }

        logger.info { "Registering in the database the user [$request.username]." }
        this.users += User(name = request.name, surname = request.surname, password = request.password, username = request.username)
        return Miwtter.RegisterUserResponse.newBuilder()
            .setResponseStatus(Miwtter.RegisterUserResponse.ResponseStatus.USER_CREATED)
            .build()
    }

    override fun loginUser(request: Miwtter.LoginUserRequest): Miwtter.LoginUserResponse {
        val userInDatabase = this.findUser(request.username)
        if(userInDatabase == null) {
            logger.info { "User [${request.username}] not found in the database." }
            return Miwtter.LoginUserResponse.newBuilder()
                .setResponseStatus(Miwtter.LoginUserResponse.ResponseStatus.INCORRECT_USERNAME_OR_PASSWORD)
                .build()
        } else if (!userInDatabase.password.contentEquals(request.password)) {
            logger.info { "User [${request.username}] found in the database. But wrong password." }
            return Miwtter.LoginUserResponse.newBuilder()
                .setResponseStatus(Miwtter.LoginUserResponse.ResponseStatus.INCORRECT_USERNAME_OR_PASSWORD)
                .build()
        } else {
            logger.info("Login successful")
            return Miwtter.LoginUserResponse.newBuilder()
                .setResponseStatus(Miwtter.LoginUserResponse.ResponseStatus.SUCCESS)
                .build()
        }
    }

    override fun findUserByFreeText(request: Miwtter.FindUserRequest): Miwtter.FindUserResponse {
        val dbUser = this.findUser(request.username)

        return if(dbUser != null) {
            val userFeedPosts = this.posts.filter {
                post -> post.ownerUsername.contentEquals(dbUser.username)
            }.map {
                post -> Miwtter.UserPost.newBuilder()
                .setPostId(post.id)
                .setOwnerUsername(post.ownerUsername)
                .setPostContent(post.content)
                .setNumberOfLikes(post.numberOfLikes.toString())
                .build()
            }

            val userLikedPosts = dbUser.likedPosts.map {
                post -> Miwtter.FeedPost.newBuilder()
                .setPostId(post.id)
                .setOwnerUsername(post.ownerUsername)
                .setNumberOfLikes(post.numberOfLikes)
                .setContent(post.content)
                .build()
            }

            logger.info { "[${userFeedPosts.size}] posts found for user [${dbUser.username}]" }

            val fullUserData = Miwtter.FullUserData.newBuilder()
                .setName(dbUser.name)
                .setSurname(dbUser.surname)
                .setUsername(dbUser.username)
                .addAllUserPosts(userFeedPosts)
                .addAllLikedPosts(userLikedPosts)
                .build()

            Miwtter.FindUserResponse.newBuilder()
                .addUser(fullUserData)
                .build()
        } else {
            Miwtter.FindUserResponse.newBuilder()
                .build()
        }
    }

    override fun createPost(request: Miwtter.CreatePostRequest): Miwtter.CreatePostResponse {
        return if(this.findUser(request.actorUsername) == null) {
            Miwtter.CreatePostResponse.newBuilder()
                .setResponseStatus(Miwtter.CreatePostResponse.ResponseStatus.USER_NOT_FOUND)
                .build()
        } else {
            val owner = this.findUser(request.actorUsername)!!
            this.posts += Post(id = this.posts.size.toString(), ownerUsername = request.actorUsername, ownerName = owner.name,content = request.content)
            Miwtter.CreatePostResponse.newBuilder()
                .setResponseStatus(Miwtter.CreatePostResponse.ResponseStatus.POST_CREATED)
                .build()
        }
    }

    override fun addLikeToPost(request: Miwtter.LikePostRequest): Miwtter.LikePostResponse {
        val dbPost = this.findPost(request.postId)
        val dbUser = this.findUser(request.actorUsername)

        return if(dbPost == null) {
            Miwtter.LikePostResponse.newBuilder()
                .setResponseStatus(Miwtter.LikePostResponse.ResponseStatus.POST_NOT_FOUND)
                .build()
        } else if(dbUser == null) {
            Miwtter.LikePostResponse.newBuilder()
                .setResponseStatus(Miwtter.LikePostResponse.ResponseStatus.USER_NOT_FOUND)
                .build()
        } else if(dbUser.likedPosts.contains(dbPost)) {
            Miwtter.LikePostResponse.newBuilder()
                .setResponseStatus(Miwtter.LikePostResponse.ResponseStatus.LIKE_CREATED)
                .build()
        } else {
            logger.info { "Adding a like to the post [${request.postId}] by user [${request.actorUsername}]. Moving from [${dbPost.numberOfLikes}] -> [${dbPost.numberOfLikes + 1}]" }
            dbPost.numberOfLikes = dbPost.numberOfLikes + 1
            dbUser.likedPosts.add(dbPost)
            Miwtter.LikePostResponse.newBuilder()
                .setResponseStatus(Miwtter.LikePostResponse.ResponseStatus.LIKE_CREATED)
                .build()
        }
    }

    override fun removeLike(request: Miwtter.RemoveLikeRequest): Miwtter.RemoveLikeResponse {
        val dbPost = this.findPost(request.postId)
        val dbUser = this.findUser(request.actorUsername)

        return if(dbPost == null) {
            Miwtter.RemoveLikeResponse.newBuilder()
                .setResponseStatus(Miwtter.RemoveLikeResponse.ResponseStatus.POST_NOT_FOUND)
                .build()
        } else if(dbUser == null) {
            Miwtter.RemoveLikeResponse.newBuilder()
                .setResponseStatus(Miwtter.RemoveLikeResponse.ResponseStatus.USER_NOT_FOUND)
                .build()
        } else if(!dbUser.likedPosts.contains(dbPost)) {
            Miwtter.RemoveLikeResponse.newBuilder()
                .setResponseStatus(Miwtter.RemoveLikeResponse.ResponseStatus.LIKE_DID_NOT_EXISTS)
                .build()
        } else {
            dbPost.numberOfLikes = dbPost.numberOfLikes - 1
            dbUser.likedPosts.remove(dbPost)
            Miwtter.RemoveLikeResponse.newBuilder()
                .setResponseStatus(Miwtter.RemoveLikeResponse.ResponseStatus.LIKE_REMOVED)
                .build()
        }
    }

    override fun getFeedForUser(request: Miwtter.GetFeedRequest): Miwtter.GetFeedResponse {
        var feedPosts = ArrayList<Miwtter.FeedPost>()
        this.posts.forEach {
            post -> feedPosts.add(
                Miwtter.FeedPost.newBuilder()
                    .setPostId(post.id)
                    .setOwnerUsername(post.ownerUsername)
                    .setOwnerName(post.ownerName)
                    .setContent(post.content)
                    .setNumberOfLikes(post.numberOfLikes)
                    .build()
            )
        }

        return Miwtter.GetFeedResponse.newBuilder()
            .setResponseStatus(Miwtter.GetFeedResponse.ResponseStatus.FEED_FOUND)
            .addAllPosts(feedPosts)
            .build()
    }

}