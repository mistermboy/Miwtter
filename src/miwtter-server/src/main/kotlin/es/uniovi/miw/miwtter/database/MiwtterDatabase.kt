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
     * Sometimes it is not very clear which user you want to search for, but if you have an indication of what your
     * name, surname or username may be. To do this, this method allows you to search for any field of a user and
     * apply OR or AND search types depending on the policy that is applied. Thus it can be applied to achieve the
     * conjunction or intersection of several searches
     *
     * @param request is a [Miwtter.FindUserRequest] object that contains the fields to search and the search policy.
     * This search policy can be [Miwtter.FindUserRequest.FindPolicy.OR] or [Miwtter.FindUserRequest.FindPolicy.AND].
     * @return a [Miwtter.FindUserResponse] object that contains the result of the search.
     */
    fun findUserByFreeText(request: Miwtter.FindUserRequest): Miwtter.FindUserResponse

    /**
     * The action of creating a post implies that the main actor, any user, wants to publish content. Therefore when
     * this happens we say that the user creates a post. To carry out this action, this method persists the content
     * of the post and relates it to the person who created it. It is important to notice that the user that creates
     * the post must be previously registered in te system. Otherwise the post will not be created.
     *
     * @param request is a [Miwtter.CreatePostRequest] that contains the content of the post and the user that triggered
     * the action of creating a post.
     * @return a [Miwtter.CreatePostResponse] object with the corresponding response. If the user does not exists in
     * the database this event will be notified here.
     */
    fun createPost(request: Miwtter.CreatePostRequest): Miwtter.CreatePostResponse

    /**
     * The posts have a field that indicates the number of users who have indicated that they like that particular
     * post. This method allows you to register that a user has liked a specific post. For this, both the user and
     * the post have to exist. Otherwise, the event cannot be registered. It is important to notice that the event
     * is not only registered in the post but also in the user. So a user can access the posts marked as liked easily.
     *
     * @param request is a [Miwtter.LikePostRequest] object that contains the data from the post to like and the user
     * that gives the like.
     * @return a [Miwtter.LikePostResponse] object with the result status of the action. If either the post identifier
     * or the user identifier provided in the request did not existed in the database previously the response will
     * include the corresponding error.
     */
    fun addLikeToPost(request: Miwtter.LikePostRequest): Miwtter.LikePostResponse

    /**
     * This action removes the likes relationship both from the post and from the user. When this action is executed
     * both the post identifier and the user identifier provided in the request must be in the database. Otherwise will
     * return an error code.
     *
     * @param request is a [Miwtter.RemoveLikeRequest] object that contains the information about the user that removes
     * the like and the post to remove the like from.
     * @return a [Miwtter.RemoveLikeResponse] object that contains the status response of the like removal. If either
     * the user identifier or the post identifier does not exist in the database will return an error code.
     */
    fun removeLike(request: Miwtter.RemoveLikeRequest): Miwtter.RemoveLikeResponse

    /**
     * The feed is the thread of posts that each user sees on their main screen. The posts that are shown to each user
     * are different. Therefore, it is necessary to identify the user who is requesting the list of main posts. Once
     * the persistence is identified, it selects those posts that have to appear in the feed of that user and wraps
     * them in the response object. It is necessary that the identified user exists. If not, you will not be able to
     * get the posts from your feed.
     *
     * @param request is a [Miwtter.GetFeedRequest] objects that contains the user identifier needed to obtain the
     * feed for that user.
     * @return a [Miwtter.GetFeedResponse] object that contains the list of posts of the user feed if the user identifier
     * exists in the database. Otherwise will notify the error in the response status and will return and empty list.
     */
    fun getFeedForUser(request: Miwtter.GetFeedRequest): Miwtter.GetFeedResponse
}