package es.uniovi.miw.miwtter.features

import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.PostsServiceGrpc
import es.uniovi.miw.miwtter.database.inmemory.InMemoryMiwtterDatabase
import io.grpc.stub.StreamObserver

/**
 * The posts features encapsulates the features that are related with the CRUD operations of the posts.
 */
class PostsFeatures: PostsServiceGrpc.PostsServiceImplBase() {

    override fun create(request: Miwtter.CreatePostRequest, responseObserver: StreamObserver<Miwtter.CreatePostResponse>) {
        val response = InMemoryMiwtterDatabase.createPost(request)
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

    override fun like(request: Miwtter.LikePostRequest, responseObserver: StreamObserver<Miwtter.LikePostResponse>) {
        val response = InMemoryMiwtterDatabase.addLikeToPost(request)
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

    override fun removeLike(request: Miwtter.RemoveLikeRequest, responseObserver: StreamObserver<Miwtter.RemoveLikeResponse>) {
        val response = InMemoryMiwtterDatabase.removeLike(request)
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}