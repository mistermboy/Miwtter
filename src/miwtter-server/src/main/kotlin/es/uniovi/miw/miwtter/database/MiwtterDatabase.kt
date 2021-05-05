package es.uniovi.miw.miwtter.database

import es.uniovi.miw.miwtter.database.domain.Post
import es.uniovi.miw.miwtter.database.domain.User
import es.uniovi.miw.miwtter.features.authentication.login.LoginData
import es.uniovi.miw.miwtter.features.feed.CreatePostData

interface MiwtterDatabase {

    fun registerUser(user: RegisterData): Boolean

    fun loginUser(user: LoginData): Boolean

    fun findUserByFreeText(freeText: String): List<User>

    fun createPost(post: CreatePostData): Boolean

    fun addLikeToPost(actorUsername: String, postId: String)

    fun removeLike(actorUsername: String, postId: String)

    fun getFeedForUser(username: String): List<Post>

    fun getUser(username: String): User?

    fun getPost(postId: String): Post?
}