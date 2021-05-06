package es.uniovi.miw.miwtter.features

import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.UsersServiceGrpc
import es.uniovi.miw.miwtter.database.adapters.MiwtterDatabaseInMemoryAdapter
import io.grpc.stub.StreamObserver
import mu.KotlinLogging

class UserFeatures: UsersServiceGrpc.UsersServiceImplBase() {

    private val logger = KotlinLogging.logger(this.javaClass.canonicalName)

    override fun register(request: Miwtter.RegisterUserRequest, responseObserver: StreamObserver<Miwtter.RegisterUserResponse>) {

        logger.info("Register action dispatched")

        val response = MiwtterDatabaseInMemoryAdapter.registerUser(request)
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

    override fun login(request: Miwtter.LoginUserRequest, responseObserver: StreamObserver<Miwtter.LoginUserResponse>) {
        val response = MiwtterDatabaseInMemoryAdapter.loginUser(request)
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

    override fun find(request: Miwtter.FindUserRequest, responseObserver: StreamObserver<Miwtter.FindUserResponse>) {
        val response = MiwtterDatabaseInMemoryAdapter.findUserByFreeText(request)
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

}