package es.uniovi.miw.miwtter.database

import es.uniovi.miw.miwtter.Miwtter

/**
 * The Miwtter database interface acts as an architectural boundary between any specific implementation of the
 * database infrastructure and the implementation of rest of the Miwtter server. So, it defines all the operations
 * that persistence needs to take care of. That means that any specific check, like duplicated users, integrity checks
 * and any other check needs to be implemented by the persistence. <- Probably this is a mistake. But god bless
 * delegation.
 *
 * @since v1.0
 * @version v1.0
 */
interface MiwtterDatabase {

    /**
     * Register a user is the action that a users performs to create an account in the Miwtter app. It provides the
     * [Miwtter.RegisterUserRequest] that contains all the data that is need for the registration. It is important
     * to know that this method is also responsible of checking that the users does not exists in the persistence.
     *
     * @param request is the request object that contains all the user data needed for the registration.
     * @return a [Miwtter.RegisterUserResponse] with the corresponding status. The [Miwtter.RegisterUserResponse.ResponseStatus]
     * is the one that indicates the result of the method execution.
     */
    fun registerUser(request: Miwtter.RegisterUserRequest): Miwtter.RegisterUserResponse

    /**
     * Once a user has registered in the application it can authenticate itself by means of a login. For that, this
     * method receives a [Miwtter.LoginUserRequest] and it evaluates against the stored data if the user can or
     * cannot login in the application. The result of this computation is given back in the [Miwtter.LoginUserResponse]
     * response status ([Miwtter.LoginUserResponse.ResponseStatus]). It can be [Miwtter.LoginUserResponse.ResponseStatus.SUCCESS]
     * or It can be [Miwtter.LoginUserResponse.ResponseStatus.INCORRECT_USERNAME_OR_PASSWORD].
     *
     * @param request is the login request that contains the user login data.
     * @return a [Miwtter.LoginUserResponse] that contains the result of the login data evaluation.
     */
    fun loginUser(request: Miwtter.LoginUserRequest): Miwtter.LoginUserResponse

    /**
     *
     * @param
     * @return
     */
    fun findUserByFreeText(request: Miwtter.FindUserRequest): Miwtter.FindUserResponse

    /**
     *
     * @param
     * @return
     */
    fun createPost(request: Miwtter.CreatePostRequest): Miwtter.CreatePostResponse

    /**
     *
     * @param
     * @return
     */
    fun addLikeToPost(request: Miwtter.LikePostRequest): Miwtter.LikePostResponse

    /**
     *
     * @param
     * @return
     */
    fun removeLike(request: Miwtter.RemoveLikeRequest): Miwtter.RemoveLikeResponse

    /**
     *
     * @param
     * @return
     */
    fun getFeedForUser(request: Miwtter.GetFeedRequest): Miwtter.GetFeedResponse
}