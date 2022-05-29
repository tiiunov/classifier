package classifier

import scala.collection.mutable.ArrayBuffer
import scala.io.Source

trait CSVExamplesParser {

  protected def parse(filename: String, className: String): List[(String, String)] = {
    def clearMessage(str: String): String = str.replaceAll("\\P{L}|[A-Z]|[a-z]", " ")
      .replaceAll("\\s+", " ").strip()

    val bufferedSource = Source.fromFile(filename)
    val res = bufferedSource.getLines()
      .foldLeft(ArrayBuffer[String]())((res, el) =>
        res += clearMessage(el.split(";").lift(3).getOrElse("")))
      .toList.map((_, className))
    bufferedSource.close
    res
  }

}
