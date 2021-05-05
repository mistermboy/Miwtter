package es.uniovi.miw.miwtter.features.authentication.register

import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.UsersServiceGrpc
import io.grpc.stub.StreamObserver

class Register : UsersServiceGrpc.UsersServiceImplBase() {

    override fun register(request: Miwtter.RegisterUserRequest?, responseObserver: StreamObserver<Miwtter.RegisterUserResponse>) {

        // 1. Check the request data.

        // 2. Check if the database contains the user.

        // 3. Insert the user in the database.

        // 4. Answer to request.
    }

    override fun login(request: Miwtter.LoginUserRequest, responseObserver: StreamObserver<Miwtter.LoginUserResponse>) {

        // 1. Check the request data.

        // 2. Check if the user exists in the database.

        // 3. Answer to the request.
    }
}