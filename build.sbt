name := "SentenceParserTimingStudy"

version := "1.0"

scalaVersion := "2.11.7"

mainClass in (Compile, run) := Some("TimingTester")
unmanagedJars in Compile += file("lib/stanford-srparser-2014-10-23-models.jar")

libraryDependencies += "edu.stanford.nlp" % "stanford-parser" % "3.5.2"
libraryDependencies += "edu.stanford.nlp" % "stanford-corenlp" % "3.5.2"

libraryDependencies +=  "io.argonaut" %% "argonaut" % "6.1-M4" changing()

libraryDependencies += "edu.stanford.nlp" % "stanford-corenlp" % "3.5.2" artifacts (Artifact("stanford-corenlp", "models"), Artifact("stanford-corenlp"))
