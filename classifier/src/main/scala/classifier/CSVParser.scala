package classifier

import scala.collection.mutable.ArrayBuffer

trait CSVParser {

  def parse(filename: String, className: String): List[(String, String)] = {
    def clearMessage(str: String): String = str.replaceAll("\\P{L}", " ")

    val bufferedSource = io.Source.fromFile(filename)
    val res = bufferedSource.getLines()
      .foldLeft(ArrayBuffer[String]())((res, el) =>
        res += clearMessage(el.split(";").lift(3).getOrElse("")))
      .toList.map((_, className))
    bufferedSource.close
    res
  }

}
