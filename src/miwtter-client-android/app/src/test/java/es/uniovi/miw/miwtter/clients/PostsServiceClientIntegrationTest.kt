package es.uniovi.miw.miwtter.clients

import es.uniovi.miw.miwtter.clients.PostServiceClient
import junit.framework.Assert.fail
import org.junit.Test
import java.lang.Exception

class PostsServiceClientIntegrationTest {

    private lateinit var service: PostServiceClient

    @Test
    fun connectionTest() {
        try {
            service = PostServiceClient()
        } catch (e: Exception) {
            e.printStackTrace()
            fail("Connection with the posts service could not be stabilised")
        }
    }
}