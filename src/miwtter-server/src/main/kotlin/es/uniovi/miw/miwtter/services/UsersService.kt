package es.uniovi.miw.miwtter.services

import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.UsersServiceGrpc
import es.uniovi.miw.miwtter.database.adapters.MiwtterDatabaseRedisAdapter
import es.uniovi.miw.miwtter.features.authentication.login.LoginData
import io.grpc.stub.StreamObserver

class UsersService : UsersServiceGrpc.UsersServiceImplBase() {

    override fun register(request: Miwtter.RegisterUserRequest, responseObserver: StreamObserver<Miwtter.RegisterUserResponse>) {

        // 1. Check the request data.
        if(!request.password.contentEquals(request.repeatedPassword)) {
            val response = Miwtter.RegisterUserResponse.newBuilder()
                .setResponseStatus(Miwtter.RegisterUserResponse.ResponseStatus.PASSWORD_NOT_MATCH_WITH_REPEATED_PASSWORD)
                .setErrorMessage("Passwords do not match")
                .build()
            responseObserver.onNext(response)
            return responseObserver.onCompleted()
        }

        // 2. Check if the database contains the user.
        if(MiwtterDatabaseRedisAdapter.getUser(request.username) != null) {
            val response = Miwtter.RegisterUserResponse.newBuilder()
                .setResponseStatus(Miwtter.RegisterUserResponse.ResponseStatus.USERNAME_ALREADY_EXISTS)
                .setErrorMessage("The username is already taken")
                .build()
            responseObserver.onNext(response)
            return responseObserver.onCompleted()
        }

        // 3. Insert the user in the database.
        MiwtterDatabaseRedisAdapter.registerUser(request)

        // 4. Answer to request.
        val response = Miwtter.RegisterUserResponse.newBuilder()
            .setResponseStatus(Miwtter.RegisterUserResponse.ResponseStatus.USER_CREATED)
            .setErrorMessage("User [${request.username}] has been registered")
            .build()
        responseObserver.onNext(response)
        return responseObserver.onCompleted()
    }

    override fun login(request: Miwtter.LoginUserRequest, responseObserver: StreamObserver<Miwtter.LoginUserResponse>) {

        // 1. Check if the user exists in the database.
        val isUserRegistered = MiwtterDatabaseRedisAdapter.loginUser(LoginData(username = request.username, password = request.password))

        // 2. Answer to the request.
        return if(isUserRegistered) {
            val response = Miwtter.LoginUserResponse.newBuilder()
                .setResponseStatus(Miwtter.LoginUserResponse.ResponseStatus.SUCCESS)
                .build()
            responseObserver.onNext(response)
            responseObserver.onCompleted()
        } else {
            val response = Miwtter.LoginUserResponse.newBuilder()
                .setResponseStatus(Miwtter.LoginUserResponse.ResponseStatus.INCORRECT_USERNAME_OR_PASSWORD)
                .build()
            responseObserver.onNext(response)
            responseObserver.onCompleted()
        }
    }

    override fun find(request: Miwtter.FindUserRequest, responseObserver: StreamObserver<Miwtter.FindUserResponse>?) {

        // 1. Check the request data.
        if(request.query.isEmpty()) {
            val response = Miwtter.FindUserResponse.newBuilder()
        }
    }
}