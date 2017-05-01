import sbt.Keys.{libraryDependencies, version}



lazy val commonSettings = Seq(
  name := "SimilarEntitiesWikidata",
  version := "1.0",
  scalaVersion := "2.11.8"
)
lazy val hello = taskKey[Unit]("An example task")

hello := {println("Yo Espen!")}

lazy val root = (project in file("."))
  .settings(
    commonSettings,
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" %  "test",
    libraryDependencies += "org.w3" %% "banana-jena" % "0.8.1",
    libraryDependencies += "org.apache.jena" % "apache-jena-libs" % "3.1.0",
    libraryDependencies +=  "com.lambdaworks" %% "jacks" % "2.3.3",
    libraryDependencies +=  "org.scalanlp" %% "breeze" % "0.12",
    libraryDependencies +=  "org.scalanlp" %% "breeze-natives" % "0.12",
    libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.4.17",
    libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.4.17",
    libraryDependencies += "org.apache.commons" % "commons-compress" % "1.5",
    libraryDependencies += "com.typesafe.play" %% "play-json" % "2.4.11"  )

lazy val structureFinder = project.settings(
  commonSettings,
  name := "StructureFinder",
  version:= "0.3"
).dependsOn(root)