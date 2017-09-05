package io.newsbridge.sample

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory

import scala.concurrent.ExecutionContextExecutor

object ApplicationMain extends App with Directives {

  // configurations
  val config = ConfigFactory.parseString(
    s"""
       |akka {
       |  loglevel = INFO
       |  stdout-loglevel = INFO
       |}
       |app {
       |  http-service {
       |    address = "0.0.0.0"
       |    port = 8080
       |  }
       |}
       """.stripMargin).withFallback(ConfigFactory.load())
  val address = config.getString("app.http-service.address")
  val port = config.getInt("app.http-service.port")

  // needed to run the route
  implicit val system: ActorSystem = ActorSystem("akka-http-sample", config)
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  // needed for the future map/flatMap in the end
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  // needed for shutdown properly
  sys.addShutdownHook(system.terminate())

  // logger
  val logger = Logging(system, getClass)

  // start http services
  val routes = path("hello") {
    get {
      complete {
        logger.info(s"Say Hello to world")
        "Hello world !"
      }
    }
  } ~ path("hello" / Segment) { name =>
    get {
      complete {
        logger.info(s"Say Hello to $name")
        s"Hello $name !"
      }
    }
  }

  //val bindingFuture = Http().bindAndHandle(routes, address, port)

  logger.info(s"Server online at http://$address:$port")
  //bindingFuture
    //.flatMap(_.unbind())
    //.onComplete(_ => system.terminate())

  Http().bindAndHandle(routes, address, port)
}
