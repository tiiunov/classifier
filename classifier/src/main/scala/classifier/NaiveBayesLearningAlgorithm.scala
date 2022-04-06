package classifier

class NaiveBayesLearningAlgorithm extends CSVParser {

  private val tokenize = (v: String) => v.split(' ').filter(_ != "")
  private val tokenizeTuple = (v: (String, String)) => tokenize(v._1)
  private val calculateWords = (l: List[(String, String)]) => l.map(tokenizeTuple(_).length).sum
  private var examples: List[(String, String)] = List()

  def addExample(ex: String, cl: String) {
    examples = (ex, cl) :: examples
  }


  def loadAllExamples(): Unit = {
    examples = this.parse("positive.csv", "Positive") :::
      this.parse("negative.csv", "Negative")
    // Рубцова Ю. Автоматическое построение и анализ корпуса коротких текстов (постов микроблогов)
    // для задачи разработки и тренировки тонового классификатора
    // Инженерия знаний и технологии семантического веба. – 2012. – Т. 1. – С. 109-116.
  }

  def classifier = new NaiveBayesClassifier(model)

  def model: NaiveBayesModel = {
    val docsByClass = examples.groupBy(_._2)
    val lengths = docsByClass.view.mapValues(calculateWords).toMap
    val docCounts = docsByClass.view.mapValues(_.length).toMap
    val wordsCount = docsByClass.view.mapValues(_.map(tokenizeTuple)
      .flatten.groupBy(x => x).view.mapValues(_.length).toMap).toMap
    new NaiveBayesModel(lengths, docCounts, wordsCount, dictionary.size)
  }

  def dictionary: Set[String] = examples.map(tokenizeTuple).flatten.toSet
}