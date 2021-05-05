package es.uniovi.miw.miwtter.database.adapters

import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.database.MiwtterDatabase
import es.uniovi.miw.miwtter.database.domain.Post
import es.uniovi.miw.miwtter.database.domain.User
import es.uniovi.miw.miwtter.features.authentication.login.LoginData
import es.uniovi.miw.miwtter.features.feed.CreatePostData
import mu.KotlinLogging

object MiwtterDatabaseRedisAdapter : MiwtterDatabase {

    private val logger = KotlinLogging.logger(this.javaClass.canonicalName)

    private val usersList = mutableListOf<User>()
    private val postsList = mutableListOf<Post>()

    init {
        logger.info("Local database created.")
    }

    override fun registerUser(user: Miwtter.RegisterUserRequest): Boolean {
        logger.info("Registering user [$user]")

        return if(this.usersList.any { iterator -> iterator.name.contentEquals(user.name) }) {
            false
        } else {
            val newUser = User(name = user.name, surname = user.surname, username = user.username, password = user.password)
            this.usersList.add(newUser)
        }
    }

    override fun loginUser(user: LoginData): Boolean {
        return this.usersList.any {
                iterator -> iterator.username.equals(user.username) && iterator.password.contentEquals(user.password)
        }
    }

    override fun findUserByFreeText(freeText: String): List<User> {
        return this.usersList.filter {
            iterator -> iterator.name.contentEquals(freeText) || iterator.username.contentEquals(freeText)
        }
    }

    override fun createPost(post: CreatePostData): Boolean {
        // 1. Check if the users exists.
        val existsUser = this.usersList.any {
            iterator -> iterator.username.contentEquals(post.ownerUsername)
        }

        return if(!existsUser) {
            false
        } else {
            // 2. Add the post to the database.
            this.postsList.add(Post(id = this.postsList.size.toString(), content = post.content, ownerUsername = post.ownerUsername))
        }
    }

    override fun addLikeToPost(actorUsername: String, postId: String) {
        // 1. Check that the posts exists
        val postsExists = this.postsList.any {
            iterator -> iterator.id.contentEquals(postId)
        }

        // 2. Check that the username exists
        val userExists = this.usersList.any {
                iterator -> iterator.username.contentEquals(actorUsername)
        }

        // 3. Add like to the post and the post to the user liked posts list.
        if(postsExists && userExists) {
            val post = postsList.find {
                iterator -> iterator.id.contentEquals(postId)
            }
            val user = this.usersList.find {
                iterator -> iterator.username.contentEquals(actorUsername)
            }
            if(user != null)
                post?.likes = post?.likes?.plus(user)!!
            if (post != null)
                user?.likes = user?.likes?.plus(post)!!
        }

    }

    override fun removeLike(actorUsername: String, postId: String) {
        // 1. Check that the posts exists
        val postsExists = this.postsList.any {
                iterator -> iterator.id.contentEquals(postId)
        }

        // 2. Check that the username exists
        val userExists = this.usersList.any {
                iterator -> iterator.username.contentEquals(actorUsername)
        }

        // 3. Add like to the post and the post to the user liked posts list.
        if(postsExists && userExists) {
            val post = postsList.find {
                    iterator -> iterator.id.contentEquals(postId)
            }
            val user = this.usersList.find {
                    iterator -> iterator.username.contentEquals(actorUsername)
            }
            if(user != null)
                post?.likes = post?.likes?.minus(user)!!
            if (post != null)
                user?.likes = user?.likes?.minus(post)!!
        }
    }

    override fun getFeedForUser(username: String): List<Post> {
        return this.postsList
    }

    override fun getUser(username: String): User? {
        return this.usersList.find {
            user -> user.username.contentEquals(username)
        }
    }

    override fun getPost(postId: String): Post? {
        return this.postsList.find {
            post -> post.id.contentEquals(postId)
        }
    }
}