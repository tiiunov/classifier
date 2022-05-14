import classifier.{NaiveBayesClassifier, NaiveBayesLearningAlgorithm}
import org.specs2.matcher.MatchResult
import org.specs2.mutable._


class FullClassifierTest extends Specification {

  val learningModel = new NaiveBayesLearningAlgorithm()
  learningModel.loadAllExamples()
  val c: NaiveBayesClassifier = learningModel.classifier
  "Classifier" should {

    def testTextClass(testText: String, expectedClass: String): MatchResult[String] = {
      val result = c.classify(testText)
      println(result.probability, result.highlightedText)
      result.kind must beEqualTo(expectedClass)
    }

    "workOnNegativeText" in {
      testTextClass("смерть беда сигареты убивают", "Negative")
      // Текст можно задавать в переменную testText
    }

    "workOnPositiveText" in {
      testTextClass("Цветы удача радость конфеты", "Positive")
      // Текст можно задавать в переменную testText
    }

    "workOnNeutralText" in {
      testTextClass("надо купить сигареты", "Neutral")
      // Текст можно задавать в переменную testText
    }

  }
}