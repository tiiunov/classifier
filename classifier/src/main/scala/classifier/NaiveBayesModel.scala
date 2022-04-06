package classifier

import scala.math.log

class NaiveBayesModel(lengths: Map[String, Int],
                      docCount: Map[String, Int],
                      wordCount: Map[String, Map[String, Int]],
                      dictionarySize: Int) {

  /**
   * @param c класс
   * @param w слово
   * @return логарифм оценки <code>P(w|c)</code> — вероятности слова в пределах класса
   */
  def wordLogProbability(c: String, w: String): Double =
    log((wordCount(c).getOrElse(w, 0) + 1.0) / (lengths(c).toDouble + dictionarySize))

  /**
   * @param c класс
   * @return логарифм априорной вероятности класса <code>P(c)</code>
   */
  def classLogProbability(c: String): Double = log(docCount(c).toDouble / docCount.values.sum)

  /**
   * @return множество всех классов
   */
  def classes: Set[String] = docCount.keySet
}