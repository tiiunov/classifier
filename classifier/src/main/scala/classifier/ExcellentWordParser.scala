package classifier

import org.apache.lucene.analysis.ru.RussianAnalyzer
import org.apache.lucene.analysis.tokenattributes.{CharTermAttribute, OffsetAttribute}

import scala.collection.mutable.ArrayBuffer

trait ExcellentWordParser {

  def parseTextToWords(text: String): Array[Term] = {
    val analyzer = new RussianAnalyzer()

    val ts = analyzer.tokenStream("text", text)
    ts.reset()

    val out = new ArrayBuffer[Term]

    while (ts.incrementToken()) {
      val word =
        ts.getAttribute(classOf[CharTermAttribute]).toString

      val offsets = ts.getAttribute(classOf[OffsetAttribute])

      out += Term(word, offsets.startOffset(), offsets.endOffset())
    }

    out.toArray
  }

  case class Term(word: String, start: Int, end: Int)
}
