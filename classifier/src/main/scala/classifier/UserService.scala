package classifier

import akka.actor.ActorRef
import akka.pattern.ask
import akka.util.Timeout
import classifier.UserActor._

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.language.postfixOps


class UserService(userActor: ActorRef) {
  implicit val timeout: Timeout = Timeout(10 seconds)

  def classifyText(text: String): Future[Result] =
    (userActor ? ClassifyText(text)).mapTo[Result]

  // возможность добавления новых операций с текстом и расширения функционала
}
