package es.uniovi.miw.miwtter.features

import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.PostsServiceGrpc
import es.uniovi.miw.miwtter.database.adapters.MiwtterDatabaseInMemoryAdapter
import io.grpc.stub.StreamObserver

class CreatePost: PostsServiceGrpc.PostsServiceImplBase() {

    override fun create(request: Miwtter.CreatePostRequest, responseObserver: StreamObserver<Miwtter.CreatePostResponse>) {
        val response = MiwtterDatabaseInMemoryAdapter.createPost(request)
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}