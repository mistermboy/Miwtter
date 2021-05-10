package es.uniovi.miw.miwtter.features

import es.uniovi.miw.miwtter.FeedServiceGrpc
import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.database.inmemory.InMemoryMiwtterDatabase
import io.grpc.stub.StreamObserver

/**
 * The feed features encapsulates those features registered with the timeline of posts that are shown to the user.
 */
class FeedFeatures: FeedServiceGrpc.FeedServiceImplBase() {

    override fun get(request: Miwtter.GetFeedRequest, responseObserver: StreamObserver<Miwtter.GetFeedResponse>) {
        val response = InMemoryMiwtterDatabase.getFeedForUser(request)
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}