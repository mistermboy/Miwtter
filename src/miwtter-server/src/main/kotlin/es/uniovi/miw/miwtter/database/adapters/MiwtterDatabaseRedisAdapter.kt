package es.uniovi.miw.miwtter.database.adapters

import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.database.MiwtterDatabase
import mu.KotlinLogging

object MiwtterDatabaseRedisAdapter : MiwtterDatabase {

    private val logger = KotlinLogging.logger(this.javaClass.canonicalName)


    init {
        logger.info("Local database created.")
    }

    override fun registerUser(request: Miwtter.RegisterUserRequest): Miwtter.RegisterUserResponse {
        TODO("Not yet implemented")
    }

    override fun loginUser(request: Miwtter.LoginUserRequest): Miwtter.LoginUserResponse {
        TODO("Not yet implemented")
    }

    override fun findUserByFreeText(request: Miwtter.FindUserRequest): Miwtter.FindUserResponse {
        TODO("Not yet implemented")
    }

    override fun createPost(request: Miwtter.CreatePostRequest): Miwtter.CreatePostResponse {
        TODO("Not yet implemented")
    }

    override fun addLikeToPost(request: Miwtter.LikePostRequest): Miwtter.LikePostResponse {
        TODO("Not yet implemented")
    }

    override fun removeLike(request: Miwtter.RemoveLikeRequest): Miwtter.RegisterUserResponse {
        TODO("Not yet implemented")
    }

    override fun getFeedForUser(request: Miwtter.GetFeedRequest): Miwtter.GetFeedResponse {
        TODO("Not yet implemented")
    }


}