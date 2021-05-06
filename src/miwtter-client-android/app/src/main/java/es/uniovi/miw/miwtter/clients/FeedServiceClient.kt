package es.uniovi.miw.miwtter.clients

import es.uniovi.miw.miwtter.FeedServiceGrpc
import es.uniovi.miw.miwtter.Miwtter
import io.grpc.ConnectivityState
import io.grpc.ManagedChannelBuilder
import java.lang.IllegalStateException

/**
 * The feed service client acts as a facade of the gRPC implementation for the communication
 * between the android client and the server. It receives as a constructor parameter the
 * address where the API is listening.
 *
 * @param apiServerAddress is an optional value that changes the server to which the client
 * connects. The default value for this field is 'api.miwtter.miw.wcr.es'. This endpoint should
 * be available at least until July 15, 2021.
 */
class FeedServiceClient(apiServerAddress: String = "http://api.miwtter.miw.wcr.es") {

    private val feedServiceStub: FeedServiceGrpc.FeedServiceBlockingStub

    init {
        // Create the communication channel.
        val channel = ManagedChannelBuilder.forTarget(apiServerAddress)
            .usePlaintext()
            .build()

        // Create the stub.
        feedServiceStub = FeedServiceGrpc.newBlockingStub(channel)

        // Check the connection
        val connectionState = channel.getState(true)
        if (!connectionState.equals(ConnectivityState.READY)) throw IllegalStateException("The server [$apiServerAddress] is not reachable. Current state [$connectionState].")
    }

    /**
     * Routes the get feed request to the corresponding register method of the stub.
     *
     * @param request to be routed through the stub.
     * @return the response that the gRPC server generated.
     */
    fun getFeed(request: Miwtter.GetFeedRequest): Miwtter.GetFeedResponse =
        feedServiceStub.get(request)
}