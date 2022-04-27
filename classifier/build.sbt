name := "classifier"

version := "0.1"

scalaVersion := "2.13.8"

// https://mvnrepository.com/artifact/org.specs2/specs2-core
libraryDependencies += "org.specs2" %% "specs2-core" % "4.14.1" % Test
libraryDependencies += "com.github.tototoshi" %% "scala-csv" % "1.3.10"
libraryDependencies += "org.apache.lucene" % "lucene-analyzers-common" % "8.11.1"