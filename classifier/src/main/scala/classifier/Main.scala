package classifier
import akka.http.scaladsl.marshalling.{Marshaller, ToEntityMarshaller}
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import play.twirl.api.{Html, HtmlFormat}
import views.html.form

class Main extends App {

  implicit val twirlMarshaller: ToEntityMarshaller[Html] =
    Marshaller.withFixedContentType(ContentTypes.`text/html(UTF-8)`) { html =>
      HttpEntity(ContentTypes.`text/html(UTF-8)`, html.body)
    }

  //def classifyText()
  val content: HtmlFormat.Appendable = form(None, None, None)
}
