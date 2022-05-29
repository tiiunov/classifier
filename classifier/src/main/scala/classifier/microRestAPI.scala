package classifier

import akka.http.scaladsl.marshalling.{Marshaller, ToEntityMarshaller}
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import play.twirl.api.Html
import views.html.form
import org.mdedetrich.akka.http.WebJarsSupport._


import scala.concurrent.ExecutionContext

class microRestAPI(userService: UserService)(implicit ec: ExecutionContext) {

  private def webJarAssets: Route =
    pathPrefix("webjars") {
    webJars
  }

  val routes: Route = webJarAssets ~ superPuperClassifierRoute

  implicit val twirlMarshaller: ToEntityMarshaller[Html] =
    Marshaller.withFixedContentType(ContentTypes.`text/html(UTF-8)`) { html =>
      HttpEntity(ContentTypes.`text/html(UTF-8)`, html.body)
    }


  def superPuperClassifierRoute: Route =
    pathPrefix("classify") {
      pathEndOrSingleSlash {
        get {
          complete(form(Some("Введите сюда ваш текст"), None, None ))
        } ~
          post {
            extractRequestContext { _ =>
              formField("text") {
                case field if field.isEmpty => complete(form(Some("Введите сюда ваш текст"), None, None))
                case text => val result = userService.classifyText(text)
                  onSuccess(result) { result =>
                    complete(form(Some(text), Some(result.category), Some(result.highlightedText)))
                  }
              }
            }
          }
      }
    }

}
