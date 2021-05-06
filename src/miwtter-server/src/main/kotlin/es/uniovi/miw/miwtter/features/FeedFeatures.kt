package es.uniovi.miw.miwtter.features

import es.uniovi.miw.miwtter.FeedServiceGrpc
import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.database.adapters.MiwtterDatabaseInMemoryAdapter
import io.grpc.stub.StreamObserver

class FeedFeatures: FeedServiceGrpc.FeedServiceImplBase() {

    override fun get(request: Miwtter.GetFeedRequest, responseObserver: StreamObserver<Miwtter.GetFeedResponse>) {
        val response = MiwtterDatabaseInMemoryAdapter.getFeedForUser(request)
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}