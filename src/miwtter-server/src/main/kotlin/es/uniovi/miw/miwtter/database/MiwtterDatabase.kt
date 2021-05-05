package es.uniovi.miw.miwtter.database

import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.database.domain.Post
import es.uniovi.miw.miwtter.database.domain.User
import es.uniovi.miw.miwtter.features.authentication.login.LoginData
import es.uniovi.miw.miwtter.features.feed.CreatePostData

interface MiwtterDatabase {

    fun registerUser(request: Miwtter.RegisterUserRequest): Miwtter.RegisterUserResponse

    fun loginUser(request: Miwtter.LoginUserRequest): Miwtter.LoginUserResponse

    fun findUserByFreeText(request: Miwtter.FindUserRequest): Miwtter.FindUserResponse

    fun createPost(request: Miwtter.CreatePostRequest): Miwtter.CreatePostResponse

    fun addLikeToPost(request: Miwtter.LikePostRequest): Miwtter.LikePostResponse

    fun removeLike(request: Miwtter.RemoveLikeRequest): Miwtter.RegisterUserResponse

    fun getFeedForUser(request: Miwtter.GetFeedRequest): Miwtter.GetFeedResponse
}