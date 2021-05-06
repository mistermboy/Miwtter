package es.uniovi.miw.miwtter

import es.uniovi.miw.miwtter.services.UsersService
import io.grpc.Server
import io.grpc.ServerBuilder
import mu.KotlinLogging

class MiwtterServer(private val port: Int) {

    private val logger = KotlinLogging.logger(this.javaClass.canonicalName)

    private val server: Server = ServerBuilder
        .forPort(port)
        .addService(UsersService())
        .build()

    fun start() {
        server.start()
        logger.info("Server started, listening on $port")
        Runtime.getRuntime().addShutdownHook(
            Thread {
                logger.info("*** shutting down gRPC server since JVM is shutting down")
                this@MiwtterServer.stop()
                logger.info("*** server shut down")
            }
        )
    }

    private fun stop() {
        server.shutdown()
    }

    fun blockUntilShutdown() {
        server.awaitTermination()
    }
}

fun main() {
    val port = System.getenv("PORT")?.toInt() ?: 80
    val server = MiwtterServer(port)
    server.start()
    server.blockUntilShutdown()
}