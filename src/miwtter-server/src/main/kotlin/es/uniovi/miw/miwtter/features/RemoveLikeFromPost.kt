package es.uniovi.miw.miwtter.features

import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.PostsServiceGrpc
import es.uniovi.miw.miwtter.database.adapters.MiwtterDatabaseInMemoryAdapter
import io.grpc.stub.StreamObserver

class RemoveLikeFromPost: PostsServiceGrpc.PostsServiceImplBase() {

    override fun removeLike(request: Miwtter.RemoveLikeRequest, responseObserver: StreamObserver<Miwtter.RemoveLikeResponse>) {
        val response = MiwtterDatabaseInMemoryAdapter.removeLike(request)
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}