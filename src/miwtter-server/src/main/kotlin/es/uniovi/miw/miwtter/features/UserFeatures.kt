package es.uniovi.miw.miwtter.features

import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.UsersServiceGrpc
import es.uniovi.miw.miwtter.database.inmemory.InMemoryMiwtterDatabase
import io.grpc.stub.StreamObserver
import mu.KotlinLogging

/**
 * The user features encapsulates those features related with the CRUD operations of the users.
 */
class UserFeatures: UsersServiceGrpc.UsersServiceImplBase() {

    private val logger = KotlinLogging.logger(this.javaClass.canonicalName)

    override fun register(request: Miwtter.RegisterUserRequest, responseObserver: StreamObserver<Miwtter.RegisterUserResponse>) {

        logger.info("Register action dispatched")

        val response = InMemoryMiwtterDatabase.registerUser(request)
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

    override fun login(request: Miwtter.LoginUserRequest, responseObserver: StreamObserver<Miwtter.LoginUserResponse>) {
        val response = InMemoryMiwtterDatabase.loginUser(request)
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

    override fun find(request: Miwtter.FindUserRequest, responseObserver: StreamObserver<Miwtter.FindUserResponse>) {
        val response = InMemoryMiwtterDatabase.findUserByFreeText(request)
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

}