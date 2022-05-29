package classifier

import akka.actor.Actor
import classifier.UserActor.ClassifyText

class UserActor extends Actor {

  val learningModel = new NaiveBayesLearningAlgorithm()
  learningModel.loadAllExamples()
  val classifier: NaiveBayesClassifier = learningModel.classifier

  override def receive: Receive = {
    case ClassifyText(text) =>
      val result = classifier.classify(text)
      sender() ! result

    // возможность добавления новых операций с текстом и расширения функционала
  }
}

object UserActor {

  case class ClassifyText(text: String)

  // возможность добавления новых операций с текстом и расширения функционала
}