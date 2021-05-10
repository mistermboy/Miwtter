package es.uniovi.miw.miwtter

import es.uniovi.miw.miwtter.features.*
import io.grpc.Server
import io.grpc.ServerBuilder
import mu.KotlinLogging

/**
 * This class is intended to be the main entrypoint for the server execution. It registers the services/features in the GRPC
 * server and starts it.
 *
 * @param port is an integer that represents the port in which the server will be deployed. The default value for this
 * parameter is 80. That means that the server will be deployed at port 80.
 */
class MiwtterServer(private val port: Int = 80) {

    private val logger = KotlinLogging.logger(this.javaClass.canonicalName)

    // REMEMBER TO ALWAYS REGISTER THE SERVICES HERE!!!!
    // For more help: https://github.com/alejgh/easidiomas/blob/main/src/authenticationservice/src/main/java/com/easidiomas/auth/AuthServer.java
    private val server: Server = ServerBuilder
        .forPort(port)
        .addService(PostsFeatures())
        .addService(FeedFeatures())
        .addService(UserFeatures())
        .build()

    /**
     * Starts the server at the indicated port.
     */
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

    /**
     * Stops the server.
     */
    private fun stop() {
        server.shutdown()
    }

    /**
     * Blocks the execution untill the server is shutdown.
     */
    fun blockUntilShutdown() {
        server.awaitTermination()
    }
}

/**
 * Main function. Execute with no args to run the server. It will look for the environment variable 'MIWTTER_PORT'
 * to load the port where the start the server. If no present will start the server at the 80 port.
 */
fun main() {
    val port = System.getenv("MIWTTER_PORT")?.toInt() ?: 80
    val server = MiwtterServer(port)
    server.start()
    server.blockUntilShutdown()
}