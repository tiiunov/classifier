import classifier.NaiveBayesLearningAlgorithm
import org.specs2.mutable._



class FullClassifierTest extends Specification {
  "Classifier" should {
    val c = new NaiveBayesLearningAlgorithm()
    c.loadAllExamples()

    "workOnNegativeText" in {
      val testText = "надо купить сигареты"
      val bestClass = c.classifier.classify(testText)
      bestClass must beEqualTo("Negative")
      // Текст можно задавать в переменную testText
    }

    "workOnPositiveText" in {
      val testText = "Цветы удача радость конфеты"
      val bestClass = c.classifier.classify(testText)
      bestClass must beEqualTo("Positive")
      // Текст можно задавать в переменную testText
    }
  }
}