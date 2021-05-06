package es.uniovi.miw.miwtter.database

import es.uniovi.miw.miwtter.Miwtter

interface MiwtterDatabase {

    fun registerUser(request: Miwtter.RegisterUserRequest): Miwtter.RegisterUserResponse

    fun loginUser(request: Miwtter.LoginUserRequest): Miwtter.LoginUserResponse

    fun findUserByFreeText(request: Miwtter.FindUserRequest): Miwtter.FindUserResponse

    fun createPost(request: Miwtter.CreatePostRequest): Miwtter.CreatePostResponse

    fun addLikeToPost(request: Miwtter.LikePostRequest): Miwtter.LikePostResponse

    fun removeLike(request: Miwtter.RemoveLikeRequest): Miwtter.RemoveLikeResponse

    fun getFeedForUser(request: Miwtter.GetFeedRequest): Miwtter.GetFeedResponse
}