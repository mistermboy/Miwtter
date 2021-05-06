package es.uniovi.miw.miwtter.clients

import junit.framework.Assert.fail
import org.junit.Test
import java.lang.Exception

class UsersServiceClientIntegrationTest {

    private lateinit var service: UsersServiceClient

    @Test
    fun connectionTest() {
        try {
            service = UsersServiceClient()
        } catch (e: Exception) {
            e.printStackTrace()
            fail("Connection with the users service could not be stabilised")
        }
    }
}