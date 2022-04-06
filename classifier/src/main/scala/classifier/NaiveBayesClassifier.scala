package classifier

class NaiveBayesClassifier(m: NaiveBayesModel) {

  def classify(s: String): String = {
    m.classes.toList.map(c => (c, calculateProbability(c, s))).maxBy(_._2)._1
  }

  def tokenize(s: String): Array[String] = s.split(' ')

  /**
   * Рассчитывает оценку вероятности документа в пределах класса
   * @param c класс
   * @param s текст документа
   * @return оценка <code>P(c|d)</code>
   */
  def calculateProbability(c: String, s: String): Double = {
    tokenize(s).map(m.wordLogProbability(c, _)).sum + m.classLogProbability(c)
  }
}