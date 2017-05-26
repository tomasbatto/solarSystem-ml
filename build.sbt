import AssemblyKeys._

assemblySettings

name := "ml-test"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "io.spray" % "spray-routing_2.11" % "1.3.4",
  "io.spray" % "spray-can_2.11" % "1.3.4",
  "io.spray" % "spray-json_2.11" % "1.3.3",
  "com.typesafe.akka" % "akka-actor_2.11" % "2.5.1",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "com.github.salat" % "salat-core_2.11" % "1.11.0",
  "org.mongodb" % "casbah_2.11" % "3.1.1"
)

