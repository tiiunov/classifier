name := "classifier"

version := "0.1"

scalaVersion := "2.13.8"

// https://mvnrepository.com/artifact/org.specs2/specs2-core
libraryDependencies += "org.specs2" %% "specs2-core" % "4.14.1" % Test
libraryDependencies += "org.apache.lucene" % "lucene-analyzers-common" % "8.11.1"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream"          % "2.6.8",
  "com.typesafe.akka" %% "akka-actor"           % "2.6.8",
  "com.typesafe.akka" %% "akka-http"            %  "10.2.9",
  "com.typesafe.akka" %% "akka-http-spray-json" %  "10.2.9"
)
lazy val root = (project in file(".")).enablePlugins(SbtTwirl)
