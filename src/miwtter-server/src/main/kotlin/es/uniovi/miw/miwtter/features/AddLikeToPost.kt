package es.uniovi.miw.miwtter.features

import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.PostsServiceGrpc
import es.uniovi.miw.miwtter.database.adapters.MiwtterDatabaseInMemoryAdapter
import io.grpc.stub.StreamObserver

class AddLikeToPost: PostsServiceGrpc.PostsServiceImplBase() {

    override fun like(request: Miwtter.LikePostRequest, responseObserver: StreamObserver<Miwtter.LikePostResponse>) {
        val response = MiwtterDatabaseInMemoryAdapter.addLikeToPost(request)
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}