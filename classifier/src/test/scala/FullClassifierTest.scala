import classifier.NaiveBayesLearningAlgorithm
import org.specs2.mutable._



class FullClassifierTest extends Specification {
  "Classifier" should {
    "work" in {
      val testText = "надо купить сигареты"
      val c = new NaiveBayesLearningAlgorithm()
      c.loadAllExamples()
      val bestClass = c.classifier.classify(testText)
      bestClass must beEqualTo("Negative")
      // Текст можно задавать в переменную testText
    }
  }
}