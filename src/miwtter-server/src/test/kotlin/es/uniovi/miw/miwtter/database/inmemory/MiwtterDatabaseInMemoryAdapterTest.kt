package es.uniovi.miw.miwtter.database.inmemory

import es.uniovi.miw.miwtter.Miwtter
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MiwtterDatabaseInMemoryAdapterTest {

    private lateinit var database: InMemoryMiwtterDatabase

    @BeforeEach
    fun setUp() {
        this.database = InMemoryMiwtterDatabase
        this.database.clean()

        val registerFirstUserRequest = Miwtter.RegisterUserRequest.newBuilder()
            .setName("user1")
            .setSurname("user1")
            .setUsername("user1")
            .setPassword("user1")
            .build()
        this.database.registerUser(request = registerFirstUserRequest)

        val registerSecondUserRequest = Miwtter.RegisterUserRequest.newBuilder()
            .setName("user2")
            .setSurname("user2")
            .setUsername("user2")
            .setPassword("user2")
            .build()
        this.database.registerUser(request = registerSecondUserRequest)

        val createFirstPostRequest = Miwtter.CreatePostRequest.newBuilder()
            .setActorUsername("user1")
            .setContent("Hi!")
            .build()
        this.database.createPost(createFirstPostRequest)

    }

    @Test
    fun databaseInitializationTest() {
        this.database = InMemoryMiwtterDatabase
        Assertions.assertNotNull(this.database)
    }

    @Test
    fun registerUserTest() {
        val registerRequest = Miwtter.RegisterUserRequest.newBuilder()
            .setName("Pepe")
            .setSurname("PepeS")
            .setUsername("upepe")
            .setPassword("paspepe")
            .build()

        val response = this.database.registerUser(request = registerRequest)
        Assertions.assertEquals(Miwtter.RegisterUserResponse.ResponseStatus.USER_CREATED, response.responseStatus)

        val registeredUserRequest = Miwtter.RegisterUserRequest.newBuilder()
            .setName("Pepe2")
            .setSurname("PepeS2")
            .setUsername("upepe")
            .setPassword("paspepe2")
            .build()

        val badResponse = this.database.registerUser(request = registeredUserRequest)
        Assertions.assertEquals(Miwtter.RegisterUserResponse.ResponseStatus.USERNAME_ALREADY_EXISTS, badResponse.responseStatus)
    }

    @Test
    fun loginUserTest() {
        val loginRequest = Miwtter.LoginUserRequest.newBuilder()
            .setUsername("user")
            .setPassword("pass")
            .build()

        val badResponse = this.database.loginUser(loginRequest)
        Assertions.assertEquals(Miwtter.LoginUserResponse.ResponseStatus.INCORRECT_USERNAME_OR_PASSWORD, badResponse.responseStatus)

        val registeredUserRequest = Miwtter.RegisterUserRequest.newBuilder()
            .setName("User")
            .setSurname("UserS")
            .setUsername("user")
            .setPassword("pass")
            .build()

        this.database.registerUser(request = registeredUserRequest)
        val response = this.database.loginUser(loginRequest)
        Assertions.assertEquals(Miwtter.LoginUserResponse.ResponseStatus.SUCCESS, response.responseStatus)
    }

    @Test
    fun createPostTest() {
        val badCreatePostRequest = Miwtter.CreatePostRequest.newBuilder()
            .setActorUsername("nonExistingUsername")
            .setContent("Hi!")
            .build()

        val badResponse = this.database.createPost(badCreatePostRequest)
        Assertions.assertEquals(Miwtter.CreatePostResponse.ResponseStatus.USER_NOT_FOUND, badResponse.responseStatus)

        val createPostRequest = Miwtter.CreatePostRequest.newBuilder()
            .setActorUsername("user1")
            .setContent("Hi!")
            .build()
        val response = this.database.createPost(createPostRequest)
        Assertions.assertEquals(Miwtter.CreatePostResponse.ResponseStatus.POST_CREATED, response.responseStatus)
    }

    @Test
    fun addLikeToPostTest() {

        val feed = this.database.getFeedForUser(Miwtter.GetFeedRequest.newBuilder().setActorUsername("").build())
        val samplePostId = feed.getPosts(0).postId
        val addLikeToPostRequest = Miwtter.LikePostRequest.newBuilder()
            .setPostId(samplePostId)
            .setActorUsername("user2")
            .build()

        val response = this.database.addLikeToPost(addLikeToPostRequest)
        Assertions.assertEquals(Miwtter.LikePostResponse.ResponseStatus.LIKE_CREATED, response.responseStatus)


        val badAddLikeToPostRequest = Miwtter.LikePostRequest.newBuilder()
            .setPostId(samplePostId)
            .setActorUsername("user222")
            .build()

        val badResponse = this.database.addLikeToPost(badAddLikeToPostRequest)
        Assertions.assertEquals(Miwtter.LikePostResponse.ResponseStatus.USER_NOT_FOUND, badResponse.responseStatus)
    }

    @Test
    fun getUserInfoTest() {
        var findRequest = Miwtter.FindUserRequest.newBuilder()
            .setUsername("user1")
            .setFindPolicy(Miwtter.FindUserRequest.FindPolicy.OR)
            .build()
        var response = this.database.findUserByFreeText(findRequest)
        Assertions.assertEquals(1, response.userCount)
        Assertions.assertEquals(1, response.userList[0].userPostsList.size)

        val createPostRequest = Miwtter.CreatePostRequest.newBuilder()
            .setActorUsername("user1")
            .setContent("Hi!")
            .build()
        this.database.createPost(createPostRequest)
        this.database.createPost(createPostRequest)

        response = this.database.findUserByFreeText(findRequest)
        Assertions.assertEquals(3, response.userList[0].userPostsList.size)

        val postId = response.userList[0].userPostsList[0].postId
        val addLikeToPostRequest = Miwtter.LikePostRequest.newBuilder()
            .setPostId(postId)
            .setActorUsername("user2")
            .build()
        this.database.addLikeToPost(addLikeToPostRequest)

        response = this.database.findUserByFreeText(findRequest)
        println(response.userList[0].userPostsList)
        Assertions.assertEquals("1", response.userList[0].userPostsList[0].numberOfLikes)

        findRequest = Miwtter.FindUserRequest.newBuilder()
            .setUsername("user1")
            .setFindPolicy(Miwtter.FindUserRequest.FindPolicy.OR)
            .build()

        // Repeating the like should not affect the number of likes
        this.database.addLikeToPost(addLikeToPostRequest)
        response = this.database.findUserByFreeText(findRequest)
        Assertions.assertEquals("1", response.userList[0].userPostsList[0].numberOfLikes)

        // Removing the like
        val removeRequest = Miwtter.RemoveLikeRequest.newBuilder()
            .setPostId(postId)
            .setActorUsername("user2")
            .build()
        this.database.removeLike(removeRequest)

        response = this.database.findUserByFreeText(findRequest)
        Assertions.assertEquals("0", response.userList[0].userPostsList[0].numberOfLikes)
    }
}