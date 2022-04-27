package classifier

class NaiveBayesClassifier(m: NaiveBayesModel) extends ExcellentWordParser {
  val NeutralBorder = 0.7

  // Специальный case на случай, если вероятность < NeutralBorder, для выделения Neutral класса.
  def classify(s: String): Result = {
    val ProbabilityData = m.classes.toList.map(c => (c, calculateProbability(c, s), getHighlightedString(c, s)))
    ProbabilityData.zip(m.calculateClassProbability(ProbabilityData.map(_._2))).maxBy(_._2) match {
      case ((_, _, newString), prob) if prob < NeutralBorder => Result("Neutral", prob, newString)
      case ((c, _, newString), prob) => Result(c, prob, newString)
    }
  }

  /**
   * Рассчитывает оценку вероятности документа в пределах класса
   *
   * @param c класс
   * @param s текст документа
   * @return оценка <code>P(c|d)</code>
   */
  def calculateProbability(c: String, s: String): Double = tokenize(s).map(updWord =>
    (updWord, m.wordLogProbability(c, updWord.word)))
    .map(_._2).sum + m.classLogProbability(c)

  // Помечает в строке слова, имеющие максимальную вероятность принадлежать к некоторому классу.
  def getHighlightedString(c: String, s: String): String = markString(s, tokenize(s).map(updWord =>
    (updWord, m.wordLogProbability(c, updWord.word))).sortBy(_._2).take(3)
    .flatMap(data => List(data._1.start, data._1.`end`)).sorted)

  def tokenize(s: String): Array[Term] = parseTextToWords(s)

  def markString(str: String, pos: Array[Int]): String = {
    val a = new StringBuilder(str)
    val b = pos.zipWithIndex.map { case (el, ind) => el + ind }
    b.foreach(a.insert(_, "*"))
    a.toString()
  }
}