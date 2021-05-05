package es.uniovi.miw.miwtter.clients

import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.UsersServiceGrpc
import io.grpc.ManagedChannelBuilder

class UsersServiceClient {

    val usersServiceStub: UsersServiceGrpc.UsersServiceBlockingStub

    init {
        val channel = ManagedChannelBuilder.forTarget("")
            .usePlaintext()
            .build()
        usersServiceStub = UsersServiceGrpc.newBlockingStub(channel)
    }

    fun register(request: Miwtter.RegisterUserRequest): Miwtter.RegisterUserResponse =
        usersServiceStub.register(request)

    fun login(request: Miwtter.LoginUserRequest): Miwtter.LoginUserResponse =
        usersServiceStub.login(request)

    fun find(request: Miwtter.FindUserRequest): Miwtter.FindUserResponse =
        usersServiceStub.find(request)
}