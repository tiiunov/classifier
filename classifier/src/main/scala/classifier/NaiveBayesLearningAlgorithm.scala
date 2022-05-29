package classifier

import scala.collection.mutable.ListBuffer

class NaiveBayesLearningAlgorithm extends CSVExamplesParser with ExcellentWordParser {

  private val tokenize = (v: String) => parseTextToWords(v).map(_.word)
  private val tokenizeTuple = (v: (String, String)) => tokenize(v._1)
  private val calculateWords = (l: List[(String, String)]) => l.map(tokenizeTuple(_).length).sum
  private val examples: ListBuffer[(String, String)] = ListBuffer.empty


  def addExample(ex: String, cl: String): Unit =  examples.addOne((ex, cl))


  def loadAllExamples(): Unit = {
    examples.addAll(this.parse("positive.csv", "Positive"))
    examples.addAll(this.parse("negative.csv", "Negative"))
    // Рубцова Ю. Автоматическое построение и анализ корпуса коротких текстов (постов микроблогов)
    // для задачи разработки и тренировки тонового классификатора
    // Инженерия знаний и технологии семантического веба. – 2012. – Т. 1. – С. 109-116.
  }

  def classifier = new NaiveBayesClassifier(model)

  private def model: NaiveBayesModel = {
    val docsByClass = examples.toList.groupBy(_._2)
    val lengths = docsByClass.view.mapValues(calculateWords).toMap
    val docCounts = docsByClass.view.mapValues(_.length).toMap
    val wordsCount = docsByClass.view.mapValues(_.map(tokenizeTuple)
      .flatten.groupBy(x => x).view.mapValues(_.length).toMap).toMap
    new NaiveBayesModel(lengths, docCounts, wordsCount, dictionary.size)
  }

  private def dictionary: Set[String] = examples.toList.flatMap(tuple => tokenizeTuple(tuple)).toSet
}
