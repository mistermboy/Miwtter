package es.uniovi.miw.miwtter.clients

import es.uniovi.miw.miwtter.Miwtter
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import java.lang.Exception

class UsersServiceClientIntegrationTest {

    private lateinit var service: UsersServiceClient

    @Before
    fun setUp() {
        this.service = UsersServiceClient()
    }

    @Test
    fun connectionTest() {
        try {
            service = UsersServiceClient()
        } catch (e: Exception) {
            e.printStackTrace()
            fail("Connection with the users service could not be stabilised")
        }
    }

    @Test
    fun registerUserTest() {
        val request = Miwtter.RegisterUserRequest.newBuilder()
            .setName("test-user-name")
            .setSurname("test-user-surname")
            .setUsername("test-user-username")
            .setPassword("test-user-password")
            .build()
        val response = this.service.register(request = request)
        println(response.responseStatus)
    }
}