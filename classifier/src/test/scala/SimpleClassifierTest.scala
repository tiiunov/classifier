import classifier.NaiveBayesLearningAlgorithm
import org.specs2.mutable._



class SimpleClassifierTest extends Specification {
  "Classifier" should {
    "work" in {
      val c = new NaiveBayesLearningAlgorithm()
      c.addExample("предоставляю услуги бухгалтера", "SPAM")
      c.addExample("спешите купить виагру", "SPAM")
      c.addExample("надо купить молоко", "HAM")
      val bestClass = c.classifier.classify("надо купить сигареты").kind
      bestClass must beEqualTo("Neutral")
    }
  }
}
