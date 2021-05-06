package es.uniovi.miw.miwtter.features

import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.PostsServiceGrpc
import es.uniovi.miw.miwtter.database.adapters.MiwtterDatabaseInMemoryAdapter
import io.grpc.stub.StreamObserver

class PostsFeatures: PostsServiceGrpc.PostsServiceImplBase() {

    override fun create(request: Miwtter.CreatePostRequest, responseObserver: StreamObserver<Miwtter.CreatePostResponse>) {
        val response = MiwtterDatabaseInMemoryAdapter.createPost(request)
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

    override fun like(request: Miwtter.LikePostRequest, responseObserver: StreamObserver<Miwtter.LikePostResponse>) {
        val response = MiwtterDatabaseInMemoryAdapter.addLikeToPost(request)
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

    override fun removeLike(request: Miwtter.RemoveLikeRequest, responseObserver: StreamObserver<Miwtter.RemoveLikeResponse>) {
        val response = MiwtterDatabaseInMemoryAdapter.removeLike(request)
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}