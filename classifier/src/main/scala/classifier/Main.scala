package classifier
import akka.actor.{ActorSystem, Props}

import scala.util.{Failure, Success}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route

import scala.io.StdIn


object Main extends App {
  implicit val system: ActorSystem = ActorSystem("classifier_system")
  import system.dispatcher

  val userActor = system.actorOf(Props[ClassifierActor])
  val classifierService = new ClassifierService(userActor)
  val microAPI = new microRestAPI(classifierService)
  val route : Route = microAPI.routes
  val localhost = "127.0.0.1"
  Http().newServerAt(localhost, 8080).bind(route)
    .map(_ => println(s"Server bound to $localhost"))
    .onComplete {
      case Failure(exception) =>
        println(s"Unexpected error while binding server: ${exception.getMessage}")
        system.terminate()
      case Success(_) => ()
    }

  StdIn.readLine("Press ENTER to stop\n")
  system.terminate()




}
