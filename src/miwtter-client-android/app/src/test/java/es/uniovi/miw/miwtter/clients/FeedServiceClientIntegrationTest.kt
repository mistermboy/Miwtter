package es.uniovi.miw.miwtter.clients

import es.uniovi.miw.miwtter.Miwtter
import junit.framework.Assert.fail
import org.junit.Ignore
import org.junit.Test
import java.lang.Exception

internal class FeedServiceClientIntegrationTest {

    private lateinit var service: FeedServiceClient

    @Test
    fun connectionTest() {
        try {
            service = FeedServiceClient()
        } catch (e: Exception) {
            e.printStackTrace()
            fail("Connection with the feed service could not be stabilised")
        }
    }

    @Test @Ignore
    fun getfeedTest() {
        this.service = FeedServiceClient()
        val feed = this.service.getFeed(Miwtter.GetFeedRequest.newBuilder().setActorUsername("").build())
        System.out.println(feed)
    }
}