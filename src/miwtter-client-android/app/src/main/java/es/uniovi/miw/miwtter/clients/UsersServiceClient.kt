package es.uniovi.miw.miwtter.clients

import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.UsersServiceGrpc
import io.grpc.ConnectivityState
import io.grpc.ManagedChannelBuilder

/**
 * The user service client acts as a facade of the gRPC implementation for the communication
 * between the android client and the server. It receives as a constructor parameter the
 * address where the API is listening.
 *
 * @param apiServerAddress is an optional value that changes the server to which the client
 * connects. The default value for this field is 'api.miwtter.miw.wcr.es'. This endpoint should
 * be available at least until July 15, 2021.
 */
object UsersServiceClient {

    private val usersServiceStub: UsersServiceGrpc.UsersServiceBlockingStub
    private val apiServerAddress: String = "api.miwtter.miw.wcr.es:5000"

    init {
        // Create the communication channel.
        val channel = ManagedChannelBuilder.forTarget(apiServerAddress)
                .usePlaintext()
                .build()

        // Create the stub.
        usersServiceStub = UsersServiceGrpc.newBlockingStub(channel)

        // Check the connection
        val connectionState = channel.getState(false)
        if (! (connectionState.equals(ConnectivityState.READY) || connectionState.equals(ConnectivityState.IDLE)))
            throw IllegalStateException("The server [$apiServerAddress] is not reachable. Current state [$connectionState].")
    }

    /**
     * Routes the register request to the corresponding register method of the stub.
     *
     * @param request to be routed through the stub.
     * @return the response that the gRPC server generated.
     */
    fun register(request: Miwtter.RegisterUserRequest): Miwtter.RegisterUserResponse =
            usersServiceStub.register(request)

    /**
     * Routes the login request to the corresponding register method of the stub.
     *
     * @param request to be routed through the stub.
     * @return the response that the gRPC server generated.
     */
    fun login(request: Miwtter.LoginUserRequest): Miwtter.LoginUserResponse =
            usersServiceStub.login(request)

    /**
     * Routes the find request to the corresponding register method of the stub.
     *
     * @param request to be routed through the stub.
     * @return the response that the gRPC server generated.
     */
    fun find(request: Miwtter.FindUserRequest): Miwtter.FindUserResponse =
            usersServiceStub.find(request)
}