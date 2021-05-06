package es.uniovi.miw.miwtter.features

import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.UsersServiceGrpc
import es.uniovi.miw.miwtter.database.adapters.MiwtterDatabaseInMemoryAdapter
import io.grpc.stub.StreamObserver

class LoginUser: UsersServiceGrpc.UsersServiceImplBase() {

    override fun login(request: Miwtter.LoginUserRequest, responseObserver: StreamObserver<Miwtter.LoginUserResponse>) {
        val response = MiwtterDatabaseInMemoryAdapter.loginUser(request)
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}